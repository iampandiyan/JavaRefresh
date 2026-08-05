package StaticInstanceNested;

public class NestedClassDemo {
    private int outerField = 42;

    static class StaticNested {
        void show() {
            System.out.println("Static nested class, no outer link");
            // Cannot access outerField directly
            // System.out.println(outerField); // This would cause a compilation error
        }
    }
    class Inner {
        void show() {
            System.out.println("Inner class, can access outer field: " + outerField);
        }
    }

    void demoLocalAndAnonymous(){
        int effectivelyFinalLocal = 100;

        class LocalGreeter{
            void great() {
                System.out.println("Local class sees: " + effectivelyFinalLocal);
            }
        }

        new LocalGreeter().great();

        Runnable aon=new Runnable() {
            @Override
            public void run() {
                System.out.println("Anonymous class sees: " + effectivelyFinalLocal);
            }
        };
        aon.run();
    }

    public static void main(String[] args) {
         new StaticNested().show();
        new NestedClassDemo().new Inner().show();
        new NestedClassDemo().demoLocalAndAnonymous();
    }

}
