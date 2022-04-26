import Model.Pick;
import Model.Prospect;
import Model.Team;
import View.Menu;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class MockDraftCLI {

    private Menu menu;
    private Scanner userInput;
    private List<Pick> draftOrder = new ArrayList<>();
    private List<Prospect> prospects = new ArrayList<>();
    private List<Prospect> toRemove = new ArrayList<>();
    private List<Team> teams = new ArrayList<>();

    public MockDraftCLI(Menu menu) {
        this.userInput = new Scanner(System.in);
        this.menu = menu;
    }

    public static void main(String[] args) {
        Menu menu = new Menu();
        MockDraftCLI application = new MockDraftCLI(menu);
        application.run();
    }

    public void run() {
        File leagueFile = new File("League.txt");
        File draftOrderFile = new File("DraftOrder.txt");
        File prospectFile = new File("Top60.txt");
        banner();
        this.menu.startMenu();
        String input = userInput.nextLine();
        if (input.equalsIgnoreCase("S")) {
            startDraft(draftOrderFile, prospectFile, leagueFile);
        } else if (input.equalsIgnoreCase("E")) {
            System.out.println("Your loss!");
            System.exit(1);
        } else {
            System.out.println("Invalid entry! Please restart and enter 'S' or 'E'. Thank you!");
        }
    }

    public List<Team> createLeague(File file) {
        Scanner fileScanner = null;
        try {
            fileScanner = new Scanner(file);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to create League. File not found!");
            System.exit(1);
        }
        while (fileScanner.hasNext()) {
            String line = fileScanner.nextLine();
            String[] lineArr = line.split("\\,");
            String location = lineArr[0];
            String name = lineArr[1].trim();

            Team team = new Team(location, name);
            this.teams.add(team);
        }
        return this.teams;
    }

    public List<Pick> setDraftOrder(File file) {
        Scanner fileScanner = null;
        try {
            fileScanner = new Scanner(file);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to set draft order. File not found!");
            System.exit(1);
        }
        while (fileScanner.hasNext()) {
            String line = fileScanner.nextLine();
            String[] lineArr = line.split("\\,");
            String pickNumAsString = lineArr[0];
            int pickNumAsInt = Integer.parseInt(pickNumAsString);
            String location = lineArr[1];
            String name = lineArr[2].trim();

            Team team = new Team(location, name);
            Pick pick = new Pick(pickNumAsInt, team, null);
            this.draftOrder.add(pick);
        }
        return this.draftOrder;

    }


    public List<Prospect> loadProspects(File file) {
        Scanner fileScanner = null;
        try {
            fileScanner = new Scanner(file);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to load prospects. File not found!");
            System.exit(1);
        }
        while (fileScanner.hasNext()) {
            String line = fileScanner.nextLine();
            String[] lineArr = line.split("\\,");
            String name = lineArr[0];
            String position = lineArr[1];
            String college = lineArr[2];

            Prospect prospect = new Prospect(name, position, college);
            this.prospects.add(prospect);
        }
        return this.prospects;
    }

    public void banner() {
        System.out.println("##########################################");
        System.out.println("WELCOME TO THE 2022 NFL MOCK DRAFT MACHINE");
        System.out.println("##########################################\n");
    }

    public void startDraft(File draftOrderFile, File prospectFile, File leagueFile) {
        createLeague(leagueFile);
        setDraftOrder(draftOrderFile);
        loadProspects(prospectFile);
        boolean paused = true;
        for (int i = 0; i <= 32; i++) {
            if (i == 32) {
                System.out.println("The first round of the 2022 NFL Draft is complete! Thanks for playing!");
                getRecap(draftOrder);
                System.exit(1);
            } else {
                while (paused) {
                    System.out.println("\n" + "The " + this.draftOrder.get(i).getTeam() + " are on the clock!\n");
                    this.menu.pickMenu();
                    String subMenuChoice = userInput.nextLine();
                    if (subMenuChoice.equalsIgnoreCase("D")) {
                        dMenu();
                    } else if (subMenuChoice.equalsIgnoreCase("P")) {
                        System.out.print("Make your pick: ");
                        String pick = userInput.nextLine();
                        Prospect.draftProspect(toRemove,prospects,draftOrder,pick,i);
                        i += 1;
                    } else if (subMenuChoice.equalsIgnoreCase("T")) {
                        System.out.print("Enter a team to trade with ('Packers', 'Cowboys', etc.): ");
                        String tradeTeam = userInput.nextLine();

                        for (Pick pick : draftOrder) {
                            if (pick.getTeam().getName().equalsIgnoreCase(tradeTeam)) {
                                System.out.print("Pick(s) this round: ");
                                System.out.println(pick.getPickNum() + " ");
                                System.out.println("Which pick would you like from the " + pick.getTeam() + " for your current pick: ");
//                                String pickNumberToTrade = userInput.nextLine().trim();
//                                int pickNumTrade = Integer.parseInt(pickNumberToTrade);
                                pick.setTeam(draftOrder.get(i).getTeam());
                                for (Team team : teams) {
                                    if (team.getName().equalsIgnoreCase(tradeTeam)) {
                                        draftOrder.get(i).setTeam(team);
                                    }
                                }
                            }
                        }

                    } else if (subMenuChoice.equalsIgnoreCase("F")) {
                        System.out.println("The first round of the 2022 NFL Draft is complete! Thanks for playing!");
                        getRecap(draftOrder);

                        System.exit(1);
                    }
                }
            }
        }
    }


            public void dMenu () {
                this.menu.displayProspectsMenu();
                String displayMenuChoice = userInput.nextLine();
                if (displayMenuChoice.equals("1")) {
                    System.out.print("Choose position group (QB, RB, WR, TE, OL, DL, Edge, LB, CB, S): ");
                    String position = userInput.nextLine();
                    Prospect.getProspectByPosition(prospects, position);
                } else if (displayMenuChoice.equals("2")) {
                    System.out.print("Choose school: ");
                    String collegeChoice = userInput.nextLine();
                    Prospect.getProspectBySchool(prospects, collegeChoice);
                } else if (displayMenuChoice.equals("3")) {
                    Prospect.getAllAvailableProspects(prospects);
                }

            }

            public void getRecap (List < Pick > draftRecap) {
                for (Pick pick : draftRecap) {
                    System.out.println(pick);
                }
            }
        }
