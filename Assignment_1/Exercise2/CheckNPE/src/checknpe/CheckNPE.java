package checknpe;

import java.lang.reflect.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;


/**
 * Given an array of Java classes names this class check if those classes are NPE sensible
 * i.e., if invoking a constructor or a method with default parameter values a {@code NullPointerException} is thrown.
 * In particular, the default value of a numeric primitive type parameter is 0, 
 * of a boolean parameter is false, of a reference type parameter is null.
 * @author Andrea Bruno 585457
 */

public class CheckNPE {

    private static String currentMtd;
    private static String currentCst;
    private final static String GREEN = "\u001B[32m";
    private final static String BLUE = "\u001B[34m";
    private final static String DEFAULT = "\u001B[0m";
    
    /**
     * This method tests all the constructors within a given class {@code clazz}
     * @param clazz
     */
    private static void testConstructors(String clazz) {
        System.out.println("::::::\tConstructors\t::::::");
        try {
            //Create an object associated with the class given by @clazz
            Class myClass = Class.forName(clazz);
            
            //Retrieve all declared constructors
            Constructor[] cstList = myClass.getDeclaredConstructors();

            
            //For each constructor
            for (Constructor constr : cstList) {
                
                //Get his own parameters list
                Class[] listPar = constr.getParameterTypes();
                
                //Since the type of those parameters is not homogeneous an ArrayList has been used
                ArrayList arguments = new ArrayList(Arrays.asList(listPar));

                //Update the current constructor
                updateCurrentCst(constr, listPar);

                try {
                    //For each parameter in the arraylist
                    for (int i = 0; i < listPar.length; i++) {
                        
                        //Make a cast to "Class" in order to exploit his own methods
                        Class arg = (Class) arguments.get(i);
                        
                        //Here it's checked first if this argument is a boolean,
                        //after if it is a numeric type,and finally if it's a reference type.
                        //This because, every primitive type can contain a number, except the boolean one,
                        if (arg.getName().equals("boolean")) {
                            arguments.set(i, false);
                        } else if (arg.isPrimitive()) {
                            arguments.set(i, 0);
                        } else {
                            arguments.set(i, null);
                        }
                    }
                    Object[] tmp = arguments.toArray();
                    
                    //Instantiate a new Object of the given class
                    constr.newInstance(tmp);
                
                } catch (InvocationTargetException e) {
                    //If there's an exception made by "newInstance(..)" and it's a NullPointerException
                    //print in GREEN that this constructor is NPE sensible
                    if (e.getTargetException() instanceof NullPointerException) {
                        System.out.println(GREEN + "> NPE detected while executing constructor: \"" + currentCst + "\"" + DEFAULT);
                    }
                }
            }
        } catch (Exception e) {
            //OPTIONAL
            //System.err.println(e.toString());
        } finally {
            System.out.println("\n\n");
        }
    }

    /**
     * This method tests all the methods within a given class {@code clazz}
     * @param clazz
     */
    private static void testMethods(String clazz) {
        System.out.println("::::::\tMethods\t::::::");
        try {

            //Create an object associated with the class given by @clazz
            Class myClass = Class.forName(clazz);

            //Retrieve all declared methods
            Method[] m = myClass.getDeclaredMethods();

            //Create an instance of the given class
            Object obj = myClass.newInstance();

            //For each method
            for (Method method : m) {
                currentMtd = method.getName();

                //Get his own parameters list
                Object[] listPar = method.getParameterTypes();

                //Since the type of those parameters is not homogeneous an ArrayList has been used
                ArrayList arguments = new ArrayList(Arrays.asList(listPar));

                //Update the current method
                updateCurrentMtd(method, method.getParameterTypes());

                try {
                    for (int i = 0; i < listPar.length; i++) {
                        //Make a cast to "Class" in order to exploit his own methods
                        Class arg = (Class) arguments.get(i);

                        //Here it's checked first if this argument is a boolean,
                        //after if it is a numeric type,and finally if it's a reference type.
                        //This because, every primitive type can contain a number, except the boolean one,
                        if (arg.getName().equals("boolean")) {
                            arguments.set(i, false);
                        } else if (arg.isPrimitive()) {
                            arguments.set(i, 0);
                        } else {
                            arguments.set(i, null);
                        }
                    }
                    Object[] tmp = arguments.toArray();

                    //invoke the method over @obj with his own parameters
                    method.invoke(obj, tmp);

                } catch (InvocationTargetException exc) {
                    //If there's an exception made by "invoke(..)" and it's a NullPointerException
                    //print in BLUE that this method is NPE sensible
                    if (exc.getTargetException() instanceof NullPointerException) {
                        System.out.println(BLUE + "> NPE detected while executing method: \"" + currentMtd + "\"" + DEFAULT);
                    }
                } catch (IllegalAccessException | IllegalArgumentException exc) {
                    //if there's any other exception print it
                    String tmp = exc.toString();
                    tmp = new StringTokenizer(tmp, ":").nextToken();
                    System.err.println(tmp + "\t Method: " + currentMtd);
                } catch (Exception exc) {
                    //OPTIONAL
                    //System.err.println(exc.toString());
                }
            }

        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | SecurityException e) {
            //OPTIONAL
            //System.err.println(e.toString());
        } finally {
            System.out.println("\n\n");
        }
    }

    /**
     * Print class name in a creative way 
     * @param x
     */
    private static void printClassName(String x) {
        System.out.println("\n*****************************************************************************************");
        System.out.println("\t\t\tClass: " + x);
        System.out.println("*****************************************************************************************\n");
    }

    /**
     * A simple way to get the complete class name (i.e. {@code com.mypackage.myclass})
     * @param string a class name
     * @return a complete class name
     */
    private static String setupClassName(String string) {
        //Instantiate a new object
        CheckNPE np = new CheckNPE();
        //Get his own packageName
        String pkg = np.getClass().getPackageName();

        // check if the "x" class is contained into the same package of CheckNPE
        // then add the package name before "x" in order to avoid instantation errors
        int numToken = new StringTokenizer(string, ".").countTokens();

        if (numToken == 1) {
            string = pkg.concat("." + string);
        }

        return string;
    }

    /**
     * Given a constructor and his own parameters list,
     * this function prints in an understandable way 
     * constructor's signature, moreover, it updates {@code currentCst}
     * used to keep track of last constructor analysed 
     * @param constr
     * @param args
     */
    private static void updateCurrentCst(Constructor constr, Class[] args) {
        if (args.length > 0) {
            currentCst = constr.getName().substring(constr.getName().lastIndexOf(".") + 1) + " (";

            for (Class c : args) {
                String completeName = c.getCanonicalName();
                String reducedName = completeName.substring(completeName.lastIndexOf(".") + 1);
                currentCst = currentCst.concat(reducedName + " , ");
            }
            currentCst = currentCst.substring(0, currentCst.length() - 3) + ")";
        } else {
            currentCst = constr.getName().substring(constr.getName().lastIndexOf(".") + 1) + " ( )";
        }

        System.out.println(currentCst);

    }

    /**
     * Given a method and his own parameters list,
     * this function prints in an understandable way 
     * method's signature, moreover, it updates {@code currentMtd}
     * used to keep track of last method analysed 
     * @param constr
     * @param args
     */
    private static void updateCurrentMtd(Method method, Class[] listPar) {

        if (listPar.length > 0) {
            currentMtd = method.getName().substring(method.getName().lastIndexOf(".") + 1) + " (";

            for (Class c : listPar) {
                String completeName = c.getCanonicalName();
                String reducedName = completeName.substring(completeName.lastIndexOf(".") + 1);
                currentMtd = currentMtd.concat(reducedName + " , ");
            }
            currentMtd = currentMtd.substring(0, currentMtd.length() - 3) + ")";
        } else {
            currentMtd = method.getName().substring(method.getName().lastIndexOf(".") + 1) + " ( )";
        }
        System.out.println(currentMtd);
    }

    /**
     * @param args array of strings containing class names
     * 
     * Given a list of classes using {@code args}, 
     * this method check if any of his own constructors or methods is NPE Sensible. 
     * As a result, if a method/constructor is not NPE Sensible it will be printed in BLACK,
     * if a constructor is NPE Sensible it will be printed in GREEN,
     * and if a method is NPE Sensible it will be printed in BLUE.
     * If there will be a different exception, it will be printed in RED
     */
    public static void main(String[] args) {

        for (String str : args) {

            str = setupClassName(str);
            printClassName(str);
            testConstructors(str);
            testMethods(str);

        }
    }
}
