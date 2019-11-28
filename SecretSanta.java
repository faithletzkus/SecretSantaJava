import javax.swing.*;
import java.util.*;
import java.util.Scanner;

public class SecretSanta {

    private static List<String> participants = new ArrayList<String>();
    private static Map<String, String> santaToHuman = new HashMap<String, String>();
    private static Map<String, String> humanToSanta = new HashMap<String, String>();

    /**
     * Randomly generate a santa-human pairs for the participants given.
     * Save the pairs to the santaToHuman map and humanToSanta map.
     *
     * Note: My solution allows for solutions such as
     * Participants = [a, b, c, d]
     * SantaToHuman = {a=b, b=a, c=d, d=c}
     * This solution is comprised of two subgroups where the first person is the Santa of the second and the second is the Santa first.
     * I allow this to happen because it closer to a truly random solution.
     * However, it causes a problem if there are an odd number because the last person cannot have themself.
     * I solve for this with the remainingHumans list,
     * generating a new solution over again if the last humanRemaining is also the last Santa without a human.
     */
    public static void generateSantaSolution() {
        List<String> remainingHumans = new ArrayList<String>();
        //deep copy of participants to remainingHumans
        for (String santa : participants) {
            remainingHumans.add(santa);
        }
        for (String santa : participants) {
            String human = "";
            int santaIndex = participants.indexOf(santa);
            if (remainingHumans.size() == 1 && remainingHumans.contains(santa)) {
                generateNewSantaSolution();
            }
            else {
                while (true) {
                    int randomIndex = (int) (Math.random() * participants.size());
                    human = participants.get(randomIndex);
                    if (randomIndex != santaIndex && !santaToHuman.containsValue(human)) {
                        remainingHumans.remove(human);
                        break;
                    }
                }
                santaToHuman.put(santa, human);
                humanToSanta.put(human, santa);
            }
        }
    }


    /**
     * Erase the existing solution by clearing the santaToHuman and humanToSanta maps.
     * Then, generate a new santa solution by calling generateSantaSolution().
     */
    public static void generateNewSantaSolution() {
        santaToHuman.clear();
        humanToSanta.clear();
        generateSantaSolution();
    }

    /**
     * Create a comma separated String of all the participants in participants.
     * @return the String of all participants
     */
    public static String generateParticipantsList() {
        String allParticipants = "";
        for (String participant : participants) {
            if (participants.indexOf(participant) != 0) {
                allParticipants += ", " + participant;
            }
            else {
                allParticipants += participant;
            }
        }
        return allParticipants;
    }

    /**
     * Print the usage for the setup mode
     * The usage is all accepted inputs from the user and a brief explanation of that input's functionality.
     */
    public static void printSetupUsage() {
        System.out.print("Usage:\n");
        System.out.print("\t \"Help\" to print usage\n");
        System.out.print("\t \"List\" to get current participant list\n");
        System.out.print("\t \"Remove {name}\" to remove that name from the list\n");
        System.out.print("\t \"Done\" when participants list is complete\n");
        System.out.println("\t \"End\" to end the program");
    }
    
    /**
     * Print the usage for the game mode.
     * The usage is all accepted inputs from the user and a brief explanation of that input's functionality.
     */
    public static void printGameUsage() {
        System.out.print("\nParticipants: " + generateParticipantsList() + "\n");
        System.out.print("Regular Usage:\n");
        System.out.print("\t Type in your name, Santa, to get your Human.\n");
        System.out.print("Other Usage:\n");
        System.out.print("\t \"Help\" to print usage\n");
        System.out.print("\t \"Edit\" to edit the particpants list and generate a new solution\n");
        System.out.print("\t \"Get my santa\" to enter a Human and get their Santa\n");
        System.out.print("\t \"Generate new solution\" to generate a new set of Santa and Human pairs.\n");
        System.out.println("\t \"End\" to end the program");
    }

    /**
     * Capitalize the first letter only of the name.
     * Make all other letter lowercase.
     * @param name The name to be capitalized
     * @return The capitalized name
     */
    public static String capitalizeFirstLetterOnly(String name) {
        return Character.toString(name.charAt(0)).toUpperCase() + name.toLowerCase().substring(1);
    }

    /**
     * Check to see if name is already in the list of participants
     * @param name the name to be checked
     * @return true if the name is already in the list, else return false
     */
    public static boolean isDuplicateName(String name) {
        for (var i = 0; i < participants.size(); i++) {
            if (participants.get(i).equals(name)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Allow user to setup the game by creating a list or participants
     * Then, allow user to play the game to see what Human each Santa has
     * @param args
     */
    public static void main(String[] args) {

        //Setup
        Scanner in = new Scanner(System.in);
        printSetupUsage();

        while(true) {
            System.out.println("Type: a participants name, \"done\" to play, or \"help\" to get usage.");
            String name = in.nextLine();
            name = capitalizeFirstLetterOnly(name);
            if (isDuplicateName(name)) {
                System.out.println("The name \"" + name + "\" is already in your participants list. Please type a unique name.");
            }
            else if (name.equals("Help")) {
                printSetupUsage();
            }
            else if (name.equals("List")) {
                if (participants.size() > 0) {
                    System.out.println(generateParticipantsList());
                }
                else {
                    System.out.println("There are currently no participants in the list.");
                }
            }
            else if (name.equals("Remove") || name.equals("Remove ")) {
                System.out.println("Please type a name to remove. Usage: \"Remove {name}\"");
            }
            else if (name.length() > "Remove".length()+1 && name.substring(0, "Remove".length()).equals("Remove")) {
                String nameToBeRemoved = capitalizeFirstLetterOnly(name.substring("Remove".length() + 1, name.length()));
                if (participants.contains(nameToBeRemoved)) {
                    participants.remove(nameToBeRemoved);
                    System.out.print(nameToBeRemoved + " was removed from the participants list.\n");
                    if (participants.size() > 0) {
                        System.out.println("Current participants: " + generateParticipantsList());
                    }
                    else {
                        System.out.println("There are currently no participants in the list.");
                    }
                }
                else {
                    System.out.println(nameToBeRemoved + " is not in the participants list: " + generateParticipantsList());
                }
            }
            else if (name.equals("Done")) {
                if (participants.size() < 3) {
                    if (participants.size() == 1) {
                        System.out.print("There is only " + participants.size() + " participant in your group.\n");
                    }
                    else {
                        System.out.print("There are only " + participants.size() + " participants in your group.\n");
                    }
                    System.out.println("Please have at least 3 participants.");
                }
                else {
                    break;
                }
            }
            else if (name.equals("End")) {
                return;
            }
            else {
                participants.add(name);
            }
            System.out.println();
        }

        String allParticipants = generateParticipantsList();

        generateNewSantaSolution();

        //Game Play
        printGameUsage();

        JFrame frame = new JFrame();

        while(true) {
            System.out.println("\nParticipants: " + allParticipants);
            System.out.println("What is your name, Santa? ");
            String santa = capitalizeFirstLetterOnly(in.nextLine());
            if (santaToHuman.containsKey(santa)) {
                JOptionPane.showMessageDialog(frame, "Your human is " + santaToHuman.get(santa));
            }
            else if (santa.equals("Help")) {
                printGameUsage();
            }
            else if (santa.equals("Edit")) {
                main(args);
                return;
            }
            else if (santa.equals("Get my santa")) {
                System.out.println("For which Human would you like to know the Santa?");
                String whichHuman = capitalizeFirstLetterOnly(in.nextLine());
                JOptionPane.showMessageDialog(frame, "Santa: " + humanToSanta.get(whichHuman) + ". Human: " + whichHuman);
            }
            else if (santa.equals("Generate new solution")) {
                generateNewSantaSolution();
                System.out.println("New solution generated.");
            }
            else if (santa.equals("End")) {
                System.out.println("\nThanks for playing!");
                return;
            }
            else {
                System.out.println(santa + " is not a participant. Please enter one of the participant names: " + allParticipants);
            }
        }
    }
}
