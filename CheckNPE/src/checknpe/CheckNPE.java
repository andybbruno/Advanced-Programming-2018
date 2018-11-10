package checknpe;

import java.lang.reflect.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;


/**
 *
 * @author Andrea Bruno 585457
 */

public class CheckNPE {

    private static String currentMtd;
    private static String currentCst;
    private final static String GREEN = "\u001B[32m";
    private final static String BLUE = "\u001B[34m";
    private final static String DEFAULT = "\u001B[0m";
    
    /**
     *
     * @param x
     */
    private static void testConstructors(String x) {
        System.out.println("::::::\tConstructors\t::::::");
        try {
            Class myClass = Class.forName(x);
            Constructor[] cstList = myClass.getDeclaredConstructors();

            for (Constructor constr : cstList) {

                Class[] listPar = constr.getParameterTypes();
                ArrayList arguments = new ArrayList(Arrays.asList(listPar));

                updateCurrentCst(constr, listPar);

                try {
                    for (int i = 0; i < listPar.length; i++) {
                        Class arg = (Class) arguments.get(i);

                        if (arg.getName().equals("boolean")) {
                            arguments.set(i, false);
                        } else if (arg.isPrimitive()) {
                            arguments.set(i, 0);
                        } else {
                            arguments.set(i, null);
                        }
                    }
                    Object[] tmp = arguments.toArray();
                    constr.newInstance(tmp);

                } catch (InvocationTargetException e) {
                    if (e.getTargetException() instanceof NullPointerException) {
                        System.out.println(GREEN + "> NPE detected while executing constructor: \"" + currentCst + "\"" + DEFAULT);
                    }
                }
            }
        } catch (Exception e) {
            //System.err.println(e.toString());
        } finally {
            System.out.println("\n\n");
        }
    }

    /**
     *
     * @param x
     */
    private static void testMethods(String x) {
        System.out.println("::::::\tMethods\t::::::");
        try {
            Class myClass = Class.forName(x);
            Method[] m = myClass.getDeclaredMethods();
            Object obj = myClass.newInstance();

            for (Method method : m) {
                currentMtd = method.getName();

                Object[] listPar = method.getParameterTypes();
                ArrayList arguments = new ArrayList(Arrays.asList(listPar));

                updateCurrentMtd(method, method.getParameterTypes());

                try {
                    for (int i = 0; i < listPar.length; i++) {
                        Class arg = (Class) arguments.get(i);

                        if (arg.getName().equals("boolean")) {
                            arguments.set(i, false);
                        } else if (arg.isPrimitive()) {
                            arguments.set(i, 0);
                        } else {
                            arguments.set(i, null);
                        }
                    }

                    Object[] tmp = arguments.toArray();
                    method.invoke(obj, tmp);

                } catch (InvocationTargetException exc) {
                    if (exc.getTargetException() instanceof NullPointerException) {
                        System.out.println(BLUE + "> NPE detected while executing method: \"" + currentMtd + "\"" + DEFAULT);
                    }
                } catch (IllegalAccessException | IllegalArgumentException exc) {
                    String tmp = exc.toString();
                    tmp = new StringTokenizer(tmp, ":").nextToken();
                    System.err.println(tmp + "\t Method: " + currentMtd);
                } catch (Exception exc) {
                    System.err.println(exc.toString());
                }
            }

        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | SecurityException e) {
            //System.err.println(e.toString());
        } finally {
            System.out.println("\n\n");
        }
    }

    /**
     *
     * @param x
     */
    private static void printClassName(String x) {
        System.out.println("\n*****************************************************************************************");
        System.out.println("\t\t\tClass: " + x);
        System.out.println("*****************************************************************************************\n");
    }

    /**
     *
     * @param string
     * @return
     */
    private static String setupClass(String string) {
        // A simple way to get the packageName
        CheckNPE np = new CheckNPE();
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
     *
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
     *
     * @param method
     * @param listPar
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
     *
     * @param args
     */
    public static void main(String[] args) {

        for (String str : args) {

            str = setupClass(str);
            printClassName(str);
            testConstructors(str);
            testMethods(str);

        }
    }
}
