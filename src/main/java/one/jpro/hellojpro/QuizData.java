package one.jpro.hellojpro;

import java.util.ArrayList;
import java.util.Arrays;

class QuizData {
        public ArrayList<Question> getQuestions(int i){
                ArrayList<Question> questions = new ArrayList<>();
                switch (i){
                        case 1, 2 , 3 , 4, 5, 6, 7, 8, 9, 10 ->{
                                for(int k = 0; k<8; k++ ){
                                        questions.add(QUESTIONS[k+((i*8)-8)]);
                                }
                        }
                        case 11->{
                                questions.addAll(Arrays.asList(QUESTIONS));
                        }
                }

                return questions;

        }
        public static final Question[] QUESTIONS = {
                new Question(
                        "What is the segment starting with \"Person\" doing? ",
                        "public Person(String nm, int ag, boolean ad) {\n" +
                                "    name = nm;\n" +
                                "    age = ag;\n" +
                                "    isAdult = ad;\n" +
                                "}\n" +
                                "Person harold = new Person(\"Harold\", 15, False);",
                        new String[]{
                                "It is creating a new class called Person calling from the harold object",
                                "It is creating a new object named harold calling from the Person constructor",
                                "It is creating a new method called Person using the attributes from harold",
                                "It is calling a new method called Person using the instances of harold"
                        }, 1
                ),
                new Question(
                        "Which of these lines are correctly formatted? ",
                        "System.out.println(apple);\n" +
                                "System.out.println(\"pear\");\n" +
                                "System.out.print(\"banana\");",
                        new String[]{
                                "(A) I only",
                                "(B) I and II only",
                                "(C) II and III only",
                                "(D) I, II, and III",
                                "(E) None of them"
                        },2
                ),
                new Question(
                        "Consider the following code segment. What does X equal? ",
                        "int a = 1;\nint b = 2;\nint c = 3;\nint d = 4;\ndouble x = a + b * c % d;",
                        new String[]{"A) 1", "B) 2", "C) 3", "D) 4", "E) 5"},2
                ),
                new Question(
                        "Which of the following is a correct call to test? ",
                        "public class Vehicle { public void test(Car x, SportsCar y) {} }\n" +
                                "public class Car extends Vehicle { }\n" +
                                "public class SportsCar extends Car { }\n" +
                                "Vehicle v = new Vehicle();\nCar c = new Car();\nSportsCar sporty = new SportsCar();",
                        new String[]{
                                "(A) v.test(sporty,v)",
                                "(B) sporty.test(c,c)",
                                "(C) v.test(sporty,c)",
                                "(D) sporty.test(sporty,v)",
                                "(E) c.test(sporty,sporty)"
                        },4
                ),
                new Question(
                        "What is the minimum number of times that * will be printed? ",
                        "int i = a random number such that 1 <= i <= n;\n" +
                                "for (int a = 2; a <= i; a++)\n" +
                                "    for (int b = 1; b < i; b++)\n" +
                                "        System.out.println(\"*\");",
                        new String[]{"(A) 0", "(B) 1", "(C) 2", "(D) n - 1", "(E) n - 2"},0
                ),
                new Question(
                        "Which keyword is used to inherit a class in Java? ",
                        "",
                        new String[]{"A) expand", "B) inherit", "C) extends", "D) super", "E) implements"},2
                ),
                new Question(
                        "Consider the following code segment, which is intended to find the average of two positive integers, x and y. ",
                        "Int x;\nInt y;\nInt sum = x + y;\nDouble average = (double) (total / 2);",
                        new String[]{
                                "There is no error, and the code works as intended.",
                                "In the expression (double) (total / 2), the cast to double is applied too late, so the average will be less than the expected result for even values of sum.",
                                "In the expression (double) (total / 2), the cast to double is applied too late, so the average will be greater than the expected result for even values of sum.",
                                "In the expression (double) (total / 2), the cast to double is applied too late, so the average will be less than the expected result for odd values of sum.",
                                "In the expression (double) (total / 2), the cast to double is applied too late, so the average will be greater than the expected result for odd values of sum."
                        },3
                ),
                new Question(
                        "What will be the output of the following code? ",
                        "int x = 10;\nx += 5;\nSystem.out.println(x);",
                        new String[]{"A) 5", "B) 10", "C) 15", "D) 20", "E) Compilation Error"},2
                ),
                //Start of Unit 2
                new Question(
                        "What will the following code print? ",
                        "int a = 5, b = 10;\n" +
                                "while (a < b) {\n" +
                                "    a += 2;\n" +
                                "    b -= 1;\n" +
                                "}\n" +
                                "System.out.println(a + \" \" + b);",
                        new String[]{
                                "A. 9 8",
                                "B. 10 7",
                                "C. 11 7",
                                "D. 9 7",
                                "E. Infinite loop"
                        },0
                ),

                new Question(
                        "What will be printed by the following code? ",
                        "public class Test {\n" +
                                "    public static void greet() {\n" +
                                "        System.out.println(\"Welcome to AP Computer Science!\");\n" +
                                "    }\n" +
                                "    public static void main(String[] args) {\n" +
                                "        greet();\n" +
                                "        greet();\n" +
                                "    }\n" +
                                "}",
                        new String[]{
                                "A) Welcome to AP Computer Science!",
                                "B) greet()",
                                "C) Welcome to AP Computer Science! Welcome to AP Computer Science!",
                                "D) No output",
                                "E) Compilation error"
                        },2
                ),

                new Question(
                        "Consider the following description of the Thing class which includes two constructors. Which of the following code segments, when appearing in a class other than Thing, will create an instance variable of a thing object with a value of null?\n ",
                        "Public Thing () – Constructs a Thing object that uses a default value to represent a color\n" +
                                "public Thing(String setColor) – Constructs a Thing object that uses setColor to represent a color",
                        new String[]{
                                "private Thing someThing = new Thing(\"Green\");",
                                "private Thing someThing = new Thing(\"\");",
                                "private Thing someThing = new Thing();",
                                "private Thing someThing;",
                                "private Thing(\"Green\");"
                        },2
                ),

                new Question(
                        "What is the main characteristic of a void method? ",
                        "",
                        new String[]{
                                "A) It must always return an integer.",
                                "B) It can return any type of value.",
                                "C) It does not return any value.",
                                "D) It must always return a boolean.",
                                "E) It is called without arguments."
                        },2
                ),

                new Question(
                        "Given the following method definition, How would you call this method in a main method? ",
                        "public void displayMessage() {\n" +
                                "    System.out.println(\"Hello, World!\");\n" +
                                "}",
                        new String[]{
                                "A) String message = displayMessage();",
                                "B) displayMessage();",
                                "C) System.out.println(displayMessage());",
                                "D) return displayMessage();",
                                "E) int result = displayMessage();"
                        },1
                ),

                new Question(
                        "Given the following method definition: How would you call this method in a main method and store the result? ",
                        "public int add(int a, int b) {\n" +
                                "    return a + b;\n" +
                                "}",
                        new String[]{
                                "A) add(5, 3);",
                                "B) System.out.println(add(5, 3));",
                                "C) int sum = add(5, 3);",
                                "D) add();",
                                "E) void result = add(5, 3);"
                        },2
                ),

                new Question(
                        "Consider the following description of the AnimalPrinter class which includes two methods.Assume that an AnimalPrinter object myPrinter has been properly declared and initialized inside myMethod. Which of the following code segments, if located in myMethod, will produce the intended output?\n ",
                        "public void printDog() - Displays the word \"dog\" and then moves the cursor to a new line\n" +
                                "public void printCat() – Displays the word \"cat\" and then moves the cursor to a new line",
                        new String[]{
                                "printDog();\nprintCat();",
                                "printDog(AnimalPrinter);\nprintCat(AnimalPrinter);",
                                "AnimalPrinter.printDog();\nAnimalPrinter.printCat();",
                                "printDog(myPrinter);\nprintCat(myPrinter);",
                                "myPrinter.printDog();\nmyPrinter.printCat();"
                        },4
                ),

                new Question(
                        "What is the output of the following program? ",
                        "public class Example {\n" +
                                "    public static void printTwice(String text) {\n" +
                                "        System.out.println(text);\n" +
                                "        System.out.println(text);\n" +
                                "    }\n" +
                                "    public static void main(String[] args) {\n" +
                                "        printTwice(\"AP Exam\");\n" +
                                "    }\n" +
                                "}",
                        new String[]{
                                "A) AP Exam AP Exam",
                                "B) AP Exam\\nAP Exam",
                                "C) printTwice(\"AP Exam\")",
                                "D) Compilation error",
                                "E) APExamAPExam"
                        },1
                ),
                //Start of Unit 3
                new Question(
                        "What is the purpose of the following method? ",
                        "public static boolean containsValue(int[] arr, int value) {\n" +
                                "    for (int i = 0; i < arr.length; i++) {\n" +
                                "        if (arr[i] == value) {\n" +
                                "            return true;\n" +
                                "        }\n" +
                                "    }\n" +
                                "    return false;\n" +
                                "}",
                        new String[]{
                                "A. To sort an array",
                                "B. To reverse an array",
                                "C. To find the maximum value in an array",
                                "D. To check if a value exists in an array",
                                "E. To count the number of occurrences of a value in an array"
                        },3
                ),

                new Question(
                        "Which of the following code segments is equivalent to the code below? ",
                        "if (x >= 1) x = x * 3;\n" +
                                "if (x > 3) x = 0;",
                        new String[]{
                                "(A) x = 0;",
                                "(B) if (x > 1) x = 0;",
                                "(C) if (x > 3) x = 0;",
                                "(D) if (x >= 1) x = 0;",
                                "(E) none of the above"
                        },4
                ),

                new Question(
                        "Given the following code, which of the answers best describes the conditions needed for temp to be true when it is returned? ",
                        "boolean temp = false;\n" +
                                "int count = 0;\n" +
                                "for (int testVal : a) {\n" +
                                "    if (testVal == val) {\n" +
                                "        temp = true;\n" +
                                "        return temp;\n" +
                                "    }\n" +
                                "}\n" +
                                "return temp;",
                        new String[]{
                                "(A) Whenever the first element in a is equal to val",
                                "(B) Whenever a contains any element which equals val.",
                                "(C) Whenever more than 1 element in a is equal to val.",
                                "(D) Whenever exactly 1 element in a is equal to val.",
                                "(E) Whenever the last element in a is equal to val."
                        },1
                ),

                new Question(
                        "Consider the following code segment. What is printed as a result of executing this code segment? ",
                        "int a = 3;\n" +
                                "boolean b = false;\n" +
                                "int c = 4;\n" +
                                "System.out.print(true &&(!((a != c-1) == b)));",
                        new String[]{
                                "(A) 1",
                                "(B) 0",
                                "(C) True",
                                "(D) False",
                                "(E) None of the Above"
                        },3
                ),

                new Question(
                        "What is printed as a result of executing this code segment? ",
                        "boolean a = false;\n" +
                                "boolean b = true;\n" +
                                "boolean c = true;\n" +
                                "System.out.print((c && true) || !(a || b));\n" +
                                "System.out.print(((!c || a) || (b != c)));\n" +
                                "System.out.print((!c || c) && (a || !b));",
                        new String[]{
                                "(A) TrueTrueTrue",
                                "(B) TrueFalseFalse",
                                "(C) TrueFalseTrue",
                                "(D) FalseTrueFalse",
                                "(E) FalseFalseFalse"
                        },1
                ),

                new Question(
                        "What is the value of x after the following code executes? ",
                        "int x = 5;\n" +
                                "if (x > 3) {\n" +
                                "    x = x * 2;\n" +
                                "} else {\n" +
                                "    x = x + 3;\n" +
                                "}",
                        new String[]{
                                "A. 3",
                                "B. 5",
                                "C. 10",
                                "D. 6",
                                "E. 15"
                        },2
                ),

                new Question(
                        "Consider the following method. Which of the following is printed as a result of the call mystery(1234)? ",
                        "/** @param x an int value such that x >= 0 */\n" +
                                "public void mystery(int x) {\n" +
                                "    System.out.print(x % 10);\n" +
                                "    if ((x / 10) != 0) {\n" +
                                "        mystery(x / 10);\n" +
                                "    }\n" +
                                "    System.out.print(x % 10);\n" +
                                "}",
                        new String[]{
                                "(A) 1234",
                                "(B) 4321",
                                "(C) 12344321",
                                "(D) 43211234",
                                "(E) Many digits are printed due to infinite recursion."
                        },4
                ),

                new Question(
                        "Which of the following expressions is equivalent to A || (B && A)? ",
                        "",
                        new String[]{
                                "A) A && B",
                                "B) B",
                                "C) A || B",
                                "D) A",
                                "E) B || !A"
                        },3
                ),
                //Start of Unit 4
                new Question(
                        "What will be the output of the following code? ",
                        "public class Test {\n    public static void main(String[] args) {\n        int[] arr = {1, 2, 3, 4, 5};\n        for (int i = 0; i < arr.length; i++) {\n            if (arr[i] % 2 == 0) {\n                arr[i] *= 2;\n            }\n        }\n        System.out.println(arr[3]);\n    }\n}",
                        new String[]{
                                "A) 8",
                                "B) 4",
                                "C) 10",
                                "D) 6",
                                "E) 12"
                        }, 0
                ),

                new Question(
                        "Consider the following code segment. What is printed as a result of executing the code segment? ",
                        "int count = 0; \nfor (int x = 0; x < 8; x++) { \n    for (int y = x; y < 2; y++) { \n        count++; \n    }\n}\nSystem.out.println(count);",
                        new String[]{
                                "A) 4",
                                "B) 8",
                                "C) 3",
                                "D) 16",
                                "E) 20"
                        }, 2
                ),

                new Question(
                        "What is the value of x after the code below executes? ",
                        "int x = 0;\nfor (int i = 1; i <= 5; i++) {\n    x += i;\n}",
                        new String[]{
                                "A) 5",
                                "B) 10",
                                "C) 15",
                                "D) 20",
                                "E) 21"
                        }, 2
                ),

                new Question(
                        "What is the output of the following loop? ",
                        "for (int i = 0; i < 3; i++) {\n    System.out.print(i + \" \" );\n}",
                        new String[]{
                                "A) 0 1 2 3",
                                "B) 1 2 3",
                                "C) 0 1 2",
                                "D) 3 2 1",
                                "E) Infinite loop"
                        }, 2
                ),

                new Question(
                        "What is the result of the following code? ",
                        "int sum = 0;\nfor (int i = 1; i <= 4; i++) {\n    sum += i;\n}\nSystem.out.println(sum);",
                        new String[]{
                                "A) 4",
                                "B) 6",
                                "C) 8",
                                "D) 10",
                                "E) 16"
                        }, 3
                ),

                new Question(
                        "What is the output of this nested loop? ",
                        "for (int i = 1; i <= 2; i++) {\n    for (int j = 1; j <= 2; j++) {\n        System.out.print(i + j + \" \" );\n    }\n}",
                        new String[]{
                                "A) 2 3",
                                "B) 3 4",
                                "C) 1 2 3 4",
                                "D) 2 3 3 4",
                                "E) None of the above"
                        }, 3
                ),

                new Question(
                        "Which of these loops is equivalent to the following while loop? ",
                        "int x = 0;\nwhile (x < 5) {\n    x++;\n    System.out.print(x);\n}",
                        new String[]{
                                "A) for (int x = 0; x < 5; x++) { System.out.print(x); }",
                                "B) for (int x = 1; x <= 5; x++) { System.out.print(x); }",
                                "C) for (int x = 0; x < 4; x++) { System.out.print(x + 1); }",
                                "D) Both B and C",
                                "E) None of the above"
                        }, 3
                ),

                new Question(
                        "Consider the following instance variable and method. Which of the following best describes the contents of numbers after the\n" +
                                "following statement has been executed?\n",
                        "private int[] numbers;\n/** Precondition: numbers contains int values in no particular order. */\npublic int mystery(int num) {\n    for (int k = numbers.length - 1; k >= 0; k--) {\n        if (numbers[k] < num) {\n            return k;\n        }\n    }\n    return -1;\n}",
                        new String[]{
                                "A) All values in positions 0 through m are less than n.",
                                "B) All values in positions m+1 through numbers.length-1 are less than n.",
                                "C) All values in positions m+1 through numbers.length-1 are greater than or equal to n.",
                                "D) The smallest value is at position m.",
                                "E) The largest value that is smaller than n is at position m."
                        }, 2
                ),

                //Start of Unit 5
                new Question(
                        "What does the following code output? ",
                        "for (int i = 0; i < 4; i++) {\n    for (int j = 0; j < 2; j++) {\n        System.out.print(i + j + \" \" );\n    }\n}",
                        new String[]{
                                "A) 0 1 1 2 3 3 3 4",
                                "B) 0 1 1 2 2 3 3 4",
                                "C) 0 0 1 1 2 2 3 3",
                                "D) 1 2 3 4",
                                "E) None of the above"
                        }, 1
                ),

                new Question(
                        "What is the output of the following code? ",
                        "class Test {\n    private int x;\n\n    public Test(int x) {\n        this.x = x;\n    }\n\n    public int getX() {\n        return x;\n    }\n}\n\nTest t = new Test(5);\nSystem.out.println(t.getX());",
                        new String[]{
                                "A) 0",
                                "B) 5",
                                "C) Error: Constructor not defined",
                                "D) Null"
                        }, 1
                ),

                new Question(
                        "Which of the following best describes an accessor method? ",
                        "",
                        new String[]{
                                "A) A method that updates the value of an instance variable.",
                                "B) A method that retrieves the value of an instance variable.",
                                "C) A method that deletes an instance variable.",
                                "D) A method that initializes an object.",
                                "E) A method that sets default values for an object."
                        }, 1
                ),

                new Question(
                        "What is the primary purpose of a mutator method? ",
                        "",
                        new String[]{
                                "A) To perform calculations based on instance variables.",
                                "B) To display the value of an instance variable.",
                                "C) To modify the value of an instance variable.",
                                "D) To create a new instance of a class.",
                                "E) To return a copy of the object."
                        }, 2
                ),

                new Question(
                        "Consider the following class definition. The isGreater method is intended to return true if the value of one for this BoolTest object is greater than the value of one for the BoolTest parameter other,  and false otherwise. The following code segments have been proposed to replace /* missing code */.\n Which of the following replacements for /* missing code */ can be used so that isGreater will work as intended?",
                        "public class BoolTest {\n    private int one;\n    public BoolTest(int newOne) {\n        one = newOne;\n    }\n    public int getOne() {\n        return one;\n    }\n    public boolean isGreater(BoolTest other) {\n        /* missing code */\n    }\n} \n I: return one > other.one;\n II: return one > other.getOne();\n III: return getOne() > other.one;",
                        new String[]{
                                "I only",
                                "II only",
                                "III only",
                                "I and II only",
                                "I, II and III"
                        }, 4
                ),

                new Question(
                        "Which of the following can be used to replace /* missing code */ so that advance will correctly update the time? ",
                        "public class TimeUpdate {\n" +
                                "    private int hours, minutes;\n" +
                                "    public TimeUpdate(int hours, int minutes) {\n" +
                                "        this.hours = hours;\n" +
                                "        this.minutes = minutes;\n" +
                                "    }\n" +
                                "    public void advance(int addedMinutes) {\n" +
                                "        minutes += addedMinutes;\n" +
                                "       /*missing code*/\n" +
                                "    }\n" +
                                "    public void displayTime() {\n" +
                                "        System.out.println(hours + minutes);\n" +
                                "    }\n" +
                                "    public static void main(String[] args) {\n" +
                                "        TimeUpdate time = new TimeUpdate(10, 45);\n" +
                                "        time.advance(80);\n" +
                                "        time.displayTime(); \n" +
                                "    }\n" +
                                "}\n",
                        new String[]{
                                "A) minutes = minutes % 60;",
                                "B) minutes = minutes + hours % 60;",
                                "C) hours = hours + minutes / 60; minutes = minutes % 60;",
                                "D) hours = hours + minutes % 60; minutes = minutes / 60;",
                                "E) hours = hours + minutes / 60;"
                        }, 2
                ),

                new Question(
                        "Which of the following is a correct way to call a static method from another class named Utility? ",
                        "",
                        new String[]{
                                "A) Utility.method();",
                                "B) Utility().method();",
                                "C) Utility.method(3);",
                                "D) Utility.methodName();",
                                "E) methodName();"
                        }, 3
                ),

                new Question(
                        "The Employee class will contain a String attribute for an employee’s name and a double attribute for the employee’s salary. Which of the following is the most appropriate implementation of the class? ",
                        "",
                        new String[]{
                                "A) public class Employee { public String name; public double salary; }",
                                "B) public class Employee { public String name; private double salary; }",
                                "C) public class Employee { private String name; private double salary; }",
                                "D) private class Employee { public String name; public double salary; }",
                                "E) private class Employee { private String name; private double salary; }"
                        }, 2
                ),
                //Start of Unit 6
                new Question(
                        "Which of the following data structures can dynamically change its size in Java? ",
                        "",
                        new String[]{
                                "A) ArrayList",
                                "B) int[]",
                                "C) HashMap",
                                "D) Both A and C",
                                "E) All of the above"
                        }, 3
                ),

                new Question(
                        "What is the output of the following code? ",
                        "int[] nums = {2, 4, 6, 8};\nSystem.out.println(nums[1] + nums[2]);",
                        new String[]{
                                "A) 6",
                                "B) 8",
                                "C) 10",
                                "D) 12",
                                "E) 14"
                        }, 2
                ),

                new Question(
                        "Which of the following is true about arrays in Java? ",
                        "",
                        new String[]{
                                "A) Arrays can hold only primitive types.",
                                "B) Arrays have a fixed size once created.",
                                "C) Arrays cannot be multidimensional.",
                                "D) Arrays are dynamically resized when new elements are added.",
                                "E) Arrays can store values of different types."
                        }, 1
                ),

                new Question(
                        "Which of the following correctly declares and initializes an integer array of size 5? ",
                        "",
                        new String[]{
                                "A) int[] arr = new int[5];",
                                "B) int arr = new int[5];",
                                "C) int[5] arr = new int[];",
                                "D) int arr = {1, 2, 3, 4, 5};",
                                "E) int[] arr = new int[];"
                        }, 0
                ),

                new Question(
                        "What is the output of the following code? ",
                        "int[] arr = {10, 20, 30, 40, 50};\nSystem.out.println(arr[2]);",
                        new String[]{
                                "A) 10",
                                "B) 20",
                                "C) 30",
                                "D) 40",
                                "E) 50"
                        }, 2
                ),

                new Question(
                        "Which of the following accesses the last element of an integer array arr? ",
                        "",
                        new String[]{
                                "A) arr[0]",
                                "B) arr[arr.length]",
                                "C) arr[arr.length - 1]",
                                "D) arr[length(arr)]",
                                "E) arr[-1]"
                        }, 2
                ),

                new Question(
                        "What is the output of the following code? ",
                        "int[] arr = {1, 2, 3, 4, 5};\narr[2] = 10;\nSystem.out.println(arr[2]);",
                        new String[]{
                                "A) 1",
                                "B) 2",
                                "C) 3",
                                "D) 10",
                                "E) 5"
                        }, 3
                ),

                new Question(
                        "Consider the following code segment: int[] arr = {1, 2, 3, 4, 5}; \n Which of the following code segments would correctly set the first two elements of array arr to 10 so that the new value of array arr will be {10, 10, 3, 4, 5}? ",
                        "",
                        new String[]{
                                "A) arr[0] = 10; arr[1] = 10;",
                                "B) arr[1] = 10; arr[2] = 10;",
                                "C) arr[0,1] = 10;",
                                "D) arr[1,2] = 10;",
                                "E) arr = 10, 10, 3, 4, 5;"
                        }, 0
                ),
                //Start of Unit 7
                new Question(
                        "Consider the following code segment: What is the value of sum after the code is executed? ",
                        "int p = 5;\n int q = 2;\n int sum = 0;\n while (p <= 8) { \nsum += p % q; \np++;\n q++; \n}",
                        new String[]{
                                "A) 1",
                                "B) 0",
                                "C) 13",
                                "D) 7",
                                "E) 4"
                        }, 3
                ),

                new Question(
                        "What does the following code print? ",
                        "ArrayList<Integer> list = new ArrayList<>();\nlist.add(10);\nlist.add(20);\nlist.add(30);\nSystem.out.println(list.get(1));",
                        new String[]{
                                "A) 10",
                                "B) 20",
                                "C) 30",
                                "D) IndexOutOfBoundsException",
                                "E) Error"
                        }, 1
                ),

                new Question(
                        "Consider the following instance variable and method. Which of the following is the best postcondition for checkArray? ",
                        "private int[] array;\n /** Precondition: array.length > 0 */ \n public int checkArray() { \nint loc = array.length / 2; \nfor (int k = 0; k < array.length; k++) { \nif (array[k] > array[loc]) { \nloc = k; \n} \n} \nreturn loc; \n}",
                        new String[]{
                                "A) Returns the index of the first element in array whose value is greater than array[loc]",
                                "B) Returns the index of the last element in array whose value is greater than array[loc]",
                                "C) Returns the largest value in array",
                                "D) Returns the index of the largest value in array",
                                "E) Returns the index of the largest value in the second half of array"
                        }, 3
                ),

                new Question(
                        "Consider the following proposed constructors for this class. Which of these constructors would be legal for the NamedPoint class? ",
                        "I. public NamedPoint() { name = \"\"; } \nII. public NamedPoint(int d1, int d2, String pointName) { x = d1; y = d2; name = pointName; } \nIII. public NamedPoint(int d1, int d2, String pointName) { super(d1, d2); name = pointName; } ",
                        new String[]{
                                "A) I only",
                                "B) II only",
                                "C) III only",
                                "D) I and III only",
                                "E) II and III only"
                        }, 4
                ),

                new Question(
                        "Consider the following ArrayList:  What will be the state of the ArrayList after one iteration of selection sort? ",
                        "ArrayList<String> words = new ArrayList<>(Arrays.asList(\"apple\", \"orange\", \"banana\", \"pear\"));",
                        new String[]{
                                "A) [apple, orange, banana, pear]",
                                "B) [apple, banana, orange, pear]",
                                "C) [pear, orange, banana, apple]",
                                "D) [orange, banana, apple, pear]",
                                "E) [banana, orange, apple, pear]"
                        }, 1
                ),

                new Question(
                        "Which Java library method is used to sort an ArrayList in descending order? ",
                        "",
                        new String[]{
                                "A) Collections.reverseSort(list)",
                                "B) ArrayList.sortDescending()",
                                "C) Arrays.sortDescending(list)",
                                "D) Collections.sort(list, Collections.reverseOrder())",
                                "E) ArrayList.reverseOrderSort()"
                        }, 3
                ),

                new Question(
                        "What is printed by the following code segment? ",
                        "ArrayList<Integer> numList = new ArrayList<Integer>();\nnumList.add(3);\nnumList.add(2);\nnumList.add(3);\nnumList.add(1, 0);\nnumList.set(0, 2);\nSystem.out.print(numList);",
                        new String[]{
                                "A) [1, 3, 0, 1]",
                                "B) [2, 0, 2, 1]",
                                "C) [2, 0, 2, 3]",
                                "D) [2, 3, 2, 1]",
                                "E) [3, 0, 0, 1]"
                        }, 2
                ),

                new Question(
                        "What does the following method do? ",
                        "public static void doubleValues(ArrayList<Integer> list) {\n    for (int i = 0; i < list.size(); i++) {\n        list.add(i + 1, list.get(i));\n        i++;\n    }\n}",
                        new String[]{
                                "A) Doubles each element in the list.",
                                "B) Duplicates each element in the list, maintaining order.",
                                "C) Removes every second element from the list.",
                                "D) Creates a new list with doubled values but does not modify the original.",
                                "E) It throws an IndexOutOfBoundsException."
                        }, 1
                ),

                //Start of Unit 8
                new Question(
                        "Given the following method and what would the result be when m is executed? ",
                        "public void m(int[][] p) {\n    int height = p.length;\n    for (int row = 0; row < height / 2; row++) {\n        for (int col = 0; col < p[0].length; col++) {\n            p[row][col] = p[height - row - 1][col];\n        }\n    }\n}",
                        new String[]{
                                "A) Copies the values from the top half to the bottom half of the 2D array",
                                "B) Copies the values from the left half to the right half of the 2D array",
                                "C) Copies the values from the bottom half to the top half of the 2D array",
                                "D) Copies the values from the right half to the left half of the 2D array",
                                "E) All values remain the same."
                        }, 2
                ),

                new Question(
                        "What is the output of the following code? ",
                        "int[][] arr = {\n    {1, 2, 3},\n    {4, 5, 6},\n    {7, 8, 9}\n};\nSystem.out.println(arr[1][2]);",
                        new String[]{
                                "A) 4",
                                "B) 5",
                                "C) 6",
                                "D) 9",
                                "E) 12"
                        }, 2
                ),

                new Question(
                        "What is the output of the following code? ",
                        "int[][] matrix = {\n    {10, 20, 30},\n    {40, 50, 60},\n    {70, 80, 90}\n};\nSystem.out.println(matrix[2][1]);",
                        new String[]{
                                "A) 70",
                                "B) 80",
                                "C) 90",
                                "D) Error: Index out of bounds",
                                "E) 40"
                        }, 1
                ),

                new Question(
                        "What is the output of the following code? ",
                        "int[][] arr = {{1, 2}, {3, 4}, {5, 6}};\nfor (int r = 0; r < arr.length; r++) {\n    for (int c = 0; c < arr[0].length; c++) {\n        System.out.print(arr[r][c] + \" \");\n    }\n}",
                        new String[]{
                                "A) 1 2 3 4 5 6",
                                "B) 1 3 5 2 4 6",
                                "C) 1 2\n3 4\n5 6",
                                "D) 1 4 6 3 5 2",
                                "E) None of the above"
                        }, 0
                ),

                new Question(
                        "Given the 2D array arr = {{7, 8, 9}, {10, 11, 12}}, what value does arr[1][2] represent? ",
                        "",
                        new String[]{
                                "A) 7",
                                "B) 8",
                                "C) 9",
                                "D) 12",
                                "E) 11"
                        }, 4
                ),

                new Question(
                        "What happens if the following code is executed? ",
                        "int[][] arr = {{2, 4}, {6, 8}};\nSystem.out.println(arr[2][0]);",
                        new String[]{
                                "A) Prints 6",
                                "B) Throws an ArrayIndexOutOfBoundsException",
                                "C) Prints 0",
                                "D) Throws a NullPointerException",
                                "E) None of the above"
                        }, 1
                ),

                new Question(
                        "What are the diagonal elements of the array int[][] arr = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}}? ",
                        "",
                        new String[]{
                                "A) 1, 2, 3",
                                "B) 1, 5, 9",
                                "C) 3, 5, 7",
                                "D) 7, 8, 9",
                                "E) None of the above"
                        }, 1
                ),

                new Question(
                        "If int[][] arr = {{1}, {2, 3}, {4, 5, 6}}, what does arr[2][2] represent? ",
                        "",
                        new String[]{
                                "A) 1",
                                "B) 3",
                                "C) 6",
                                "D) Throws an exception",
                                "E) None of the above"
                        }, 2
                ),

                //Start of Unit 9
                new Question(
                        "What is the output of the following code? ",
                        "public static int factorial(int n) {\n    if (n == 0) return 1;\n    return n * factorial(n - 1);\n}\nSystem.out.println(factorial(3));",
                        new String[]{
                                "A) 3",
                                "B) 5",
                                "C) 6",
                                "D) 9",
                                "E) 27"
                        }, 2
                ),

                new Question(
                        "Consider the following class definitions. The other code segment appears in a class other than Hero and SuperHero. What is printed as a result of executing the code segment?",
                        "public class Monster {\n    double health;\n    public Monster(double h) {\n        health = h;\n    }\n    public void heal(double h) {\n        health += h;\n    }\n    public double showHealth() {\n        return health;\n    }\n}\n\npublic class Boss extends Monster {\n    public Boss(double h) {\n        super(h);\n    }\n    public void heal(double h) {\n        super.heal(h + 5.5);\n    }\n}\n\npublic class Main {\n    public static void main(String[] args) {\n        Monster m;\n        m = new Boss(2.5);\n        m.heal(0.5);\n        System.out.println(m.showHealth());\n    }\n}",
                        new String[]{
                                "A) 2.5",
                                "B) 3",
                                "C) 4",
                                "D) 6.5",
                                "E) 8.5"
                        }, 4
                ),

                new Question(
                        "Which of these is an example of polymorphism in Java? ",
                        "",
                        new String[]{
                                "A) Method overloading",
                                "B) Method overriding",
                                "C) Both A and B",
                                "D) Encapsulation",
                                "E) Interfaces"
                        }, 2
                ),

                new Question(
                        "What is true about method overriding? ",
                        "",
                        new String[]{
                                "A) It requires the static keyword.",
                                "B) It happens when a method in a parent class is hidden by a child class.",
                                "C) It allows a child class to provide a specific implementation for a method in the parent class",
                                "D) It does not require inheritance.",
                                "E) It must use a different method name."
                        }, 2
                ),

                new Question(
                        "What is a key benefit of encapsulation? ",
                        "",
                        new String[]{
                                "A) It speeds up execution time.",
                                "B) It hides implementation details",
                                "C) It prevents method overriding.",
                                "D) It eliminates the need for constructors.",
                                "E) It removes the need for classes."
                        }, 1
                ),

                new Question(
                        "What is the primary purpose of a subclass in Java? ",
                        "",
                        new String[]{
                                "A) To create a separate, independent class",
                                "B) To allow inheritance and reuse of methods and fields from a superclass",
                                "C) To encapsulate unrelated data",
                                "D) To simplify the structure of unrelated classes",
                                "E) To eliminate the need for constructors"
                        }, 1
                ),

                new Question(
                        "What does the super keyword do in Java? ",
                        "",
                        new String[]{
                                "A) Calls methods from the subclass itself",
                                "B) Refers to the current object of the subclass",
                                "C) Accesses fields or methods from the superclass",
                                "D) Creates a new instance of the superclass",
                                "E) Overrides the superclass methods"
                        }, 2
                ),

                new Question(
                        "Which does NOT correctly describe a rule for overriding methods? ",
                        "",
                        new String[]{
                                "A) To override properly, both methods must have different names.",
                                "B) No additional throws clauses are permitted for checked exceptions not declared by original methods.",
                                "C) There should not be more restrictive access modifier on overridden method than on original.",
                                "D) The return type may differ as long as they are not primitives.",
                                "E) The method must have the same name as in the superclass."
                        }, 3
                ),
                //Start of Unit 10
                new Question(
                        "What is the output of mystery(5)? ",
                        "public int mystery(int n) {\n    if (n <= 1) {\n        return n;\n    }\n    return n + mystery(n - 1);\n}",
                        new String[]{
                                "A) 5",
                                "B) 10",
                                "C) 15",
                                "D) 20",
                                "E) 25"
                        }, 2
                ),

                new Question(
                        "What is the output of the following recursive function? ",
                        "public static int sum(int n) {\n    if (n == 1) return 1;\n    return n + sum(n - 1);\n}\nSystem.out.println(sum(4));",
                        new String[]{
                                "A) 4",
                                "B) 6",
                                "C) 10",
                                "D) StackOverflowError"
                        }, 3
                ),

                new Question(
                        "What is a base case in recursion? ",
                        "",
                        new String[]{
                                "A) The case where recursion begins",
                                "B) The smallest input size for which the problem can be solved",
                                "C) The condition that stops the recursion",
                                "D) The case that repeats indefinitely",
                                "E) The condition to initialize recursion results for even values of sum."
                        }, 2
                ),

                new Question(
                        "What happens if a recursive method has no base case? ",
                        "",
                        new String[]{
                                "A) It executes only once",
                                "B) It throws a NullPointerException",
                                "C) It results in a StackOverflowError",
                                "D) It returns null",
                                "E) It compiles but doesn’t execute"
                        }, 2
                ),

                new Question(
                        "Consider the following recursive method. ",
                        "public static int mystery(int n) { \n    if (n <= 1) { \n        return 0;\n    } else {\n        return 1 + mystery(n / 2); \n    } \n}\nAssuming that k is a nonnegative integer and m = 2^k, what value is returned as a result of the call mystery(m)?",
                        new String[]{
                                "A) 0",
                                "B) k",
                                "C) m",
                                "D) m^2 + 1",
                                "E) k^2 + 1"
                        }, 1
                ),

                new Question(
                        "Consider the following method. ",
                        "public static int calcMethod(int num) {\n    if (num == 5) {\n        return 10;\n    }\n    return num + calcMethod(num / 2);\n}\nWhat value is returned by the method call calcMethod(16)?",
                        new String[]{
                                "A) 10",
                                "B) 26",
                                "C) 31",
                                "D) 38",
                                "E) 41"
                        }, 4
                ),

                new Question(
                        "What does the following method calculate when sumDigits(123) is called? ",
                        "public int sumDigits(int n) {\n    if (n == 0) return 0;\n    else return (n % 10) + sumDigits(n / 10);\n}",
                        new String[]{
                                "A) The reverse of the number.",
                                "B) The largest digit in the number.",
                                "C) The sum of all digits in the number.",
                                "D) The product of all digits in the number.",
                                "E) The number of digits in the number."
                        }, 2
                ),

                new Question(
                        "What will happen if the following method is executed with printStars(3)? ",
                        "public void printStars(int n) {\n    if (n > 0) {\n        printStars(n - 1);\n        for (int i = 0; i < n; i++) System.out.print(\"*\");\n        System.out.println();\n    }\n}",
                        new String[]{
                                "A) Print a single line of ***.",
                                "B) Print nothing.",
                                "C) Print stars in increasing order, one line per level.",
                                "D) Print stars in decreasing order, one line per level.",
                                "E) Print stars in reverse pyramid format."
                        }, 2
                )
        };
}