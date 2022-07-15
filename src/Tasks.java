import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Tasks {

    static class Task {
        private int id;
        private int imaginaryM1;
        private int imaginaryM2;
        private ArrayList<Integer> machinesTimes;
        private ArrayList<Integer> inTimes;
        private ArrayList<Integer> outTimes;

        public Task(int id, int... machinesTimes) {
            this.id = id;
            this.machinesTimes = new ArrayList<Integer>();
            for (int i = 0; i < machinesTimes.length; i++) {
                this.machinesTimes.add(machinesTimes[i]);
                if (i < machinesTimes.length - 1) {
                    this.imaginaryM1 += machinesTimes[i];
                }
                if (i > 0) {
                    this.imaginaryM2 += machinesTimes[i];
                }
            }
            
        }

        public int getID() {
            return id;
        }
        
        private int getM1() {
            return imaginaryM1;
        }

        private int getM2() {
            return imaginaryM2;
        }

        @Override
        public String toString() {
            return "Task{" + "id=" + id + ", imaginary-m1=" + imaginaryM1 + ", imaginary-m2=" + imaginaryM2 + '}';
        }
    }

    ArrayList<Task> tasks;
    private ArrayList<Task> groupA;
    private ArrayList<Task> groupB;
    private boolean sequenced = false;

    public Tasks() {
        this.tasks = new ArrayList<Task>();
        this.groupA = new ArrayList<Task>();
        this.groupB = new ArrayList<Task>();
    }

    private Tasks(ArrayList<Task> tasks) {
        this.tasks = tasks;
        sequenced = true;
    }

    public void add(Task task) {
        tasks.add(task);
    }
    
    public Tasks sequence() {
        for (Task task : tasks) {
            if (task.imaginaryM1 < task.imaginaryM2) {
                groupA.add(task);
            } else {
                groupB.add(task);
            }
        }
        Collections.sort(groupA, Comparator.comparing(Task::getM1));
        Collections.sort(groupB, Comparator.comparing(Task::getM2).reversed());

        groupA.addAll(groupB);

        return new Tasks(groupA);

    }

    public int getTotalTime() throws Exception {
        if (!sequenced) {
            throw new Exception("Tasks not sequenced");
        }
        
        
        // seed fist machine of first sequence
        tasks.get(0).inTimes = new ArrayList<Integer>();
        tasks.get(0).outTimes = new ArrayList<Integer>();

        tasks.get(0).inTimes.add(0);
        tasks.get(0).outTimes.add(tasks.get(0).machinesTimes.get(0));

        for (int i = 1; i < tasks.get(0).machinesTimes.size(); i++) {
            tasks.get(0).inTimes.add(tasks.get(0).outTimes.get(i - 1));
            tasks.get(0).outTimes.add(tasks.get(0).inTimes.get(i) + tasks.get(0).machinesTimes.get(i));
        }

        
        for (int i = 1; i < tasks.size(); i++) {
            // seed fist machine of sequence
            tasks.get(i).inTimes = new ArrayList<Integer>();
            tasks.get(i).outTimes = new ArrayList<Integer>();
            tasks.get(i).inTimes.add(tasks.get(i - 1).outTimes.get(0));
            tasks.get(i).outTimes.add(tasks.get(i).inTimes.get(0) + tasks.get(i).machinesTimes.get(0));

            for (int j = 1; j < tasks.get(i).machinesTimes.size(); j++) {
                int inTime;

                // decide if last the machine has finished operation. Unless: next machine idleTime = outTime - machineTime
                if (tasks.get(i).outTimes.get(j-1) > tasks.get(i-1).outTimes.get(j)) {
                    inTime = tasks.get(i).outTimes.get(j-1);
                } else {
                    inTime = tasks.get(i-1).outTimes.get(j);
                }

                tasks.get(i).inTimes.add(inTime);
                tasks.get(i).outTimes.add(inTime + tasks.get(i).machinesTimes.get(j));

            }
        }
        
        
        return tasks.get(tasks.size() -1).outTimes.get(tasks.get(tasks.size() -1).outTimes.size() - 1);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Task task : tasks) {
            sb.append(task.toString()).append("\n");
        }
        return sb.toString();
    }

    public String sequenceAsString() {
        StringBuilder sb = new StringBuilder();
        sb.append(tasks.get(0).id + "->");
        for (int i = 1; i < tasks.size()-1; i++) {
            sb.append(tasks.get(i).id + "->");
        }
        sb.append(tasks.get(tasks.size()-1).id);
        return sb.toString();
    }
}
