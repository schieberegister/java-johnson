public class Main {
    
    public static void main(String[] args) throws Exception {
    
    // Example 1
    
    Tasks tasks = new Tasks();
    
    tasks.add(new Tasks.Task(1, 2, 5));
    tasks.add(new Tasks.Task(2, 3, 2));
    tasks.add(new Tasks.Task(3, 3, 1));
    tasks.add(new Tasks.Task(4, 2, 6));
    tasks.add(new Tasks.Task(5, 5, 3));
    tasks.add(new Tasks.Task(6, 8, 4));

    Tasks sequenced  = tasks.sequence();

    System.out.println();
    System.out.println("Example Type:          A simple two machine problem");
    System.out.println("Sequence:              " + sequenced.sequenceAsString());
    
    try {
        System.out.println("Total processing time: " + sequenced.getTotalTime());
    } catch (Exception e) {
        System.out.println(e.getMessage());
    }

    System.out.println();

    // Example 2
    
    tasks = new Tasks();

    tasks.add(new Tasks.Task(1, 1, 7, 8));
    tasks.add(new Tasks.Task(2, 3, 3, 10));
    tasks.add(new Tasks.Task(3, 7, 8, 9));
    tasks.add(new Tasks.Task(4, 9, 2, 11));
    tasks.add(new Tasks.Task(5, 4, 8, 9));
    tasks.add(new Tasks.Task(6, 5, 6, 14));
    tasks.add(new Tasks.Task(7, 2, 1, 12));

    sequenced  = tasks.sequence();

    System.out.println();
    System.out.println("Example Type:          3 Machines with reduction");
    System.out.println("Sequence:              " + sequenced.sequenceAsString());
    
    try {
        System.out.println("Total processing time: " + sequenced.getTotalTime());
    } catch (Exception e) {
        System.out.println(e.getMessage());
    }

    System.out.println();

    // Example 3

    tasks = new Tasks();

    tasks.add(new Tasks.Task(1, 3, 4, 1, 12));
    tasks.add(new Tasks.Task(2, 8, 0, 5, 15));
    tasks.add(new Tasks.Task(3, 11, 3, 8, 10));
    tasks.add(new Tasks.Task(4, 4, 7, 3, 8));
    tasks.add(new Tasks.Task(5, 5, 5, 1, 10));
    tasks.add(new Tasks.Task(6, 10, 2, 0, 13));
    tasks.add(new Tasks.Task(7, 2, 5, 6, 9));

    sequenced  = tasks.sequence();

    System.out.println();
    System.out.println("Example Type:          4 Machines with reduction");
    System.out.println("Sequence:              " + sequenced.sequenceAsString());
    
    try {
        System.out.println("Total processing time: " + sequenced.getTotalTime());
    } catch (Exception e) {
        System.out.println(e.getMessage());
    }

    System.out.println();
    
    }
}
