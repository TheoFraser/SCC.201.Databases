import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.Scanner;
import java.io.BufferedReader;  
import java.io.FileReader;  
import java.io.IOException;  
import java.util.ArrayList;

public class Connect
{
    ArrayList<Integer> accountNumber = new ArrayList<Integer>();
    ArrayList<String> forename = new ArrayList<String>();
    ArrayList<String> surname = new ArrayList<String>();
    ArrayList<String> emailAddress = new ArrayList<String>();
    ArrayList<String> characterCreationDate = new ArrayList<String>();
    ArrayList<String> characterExpiraryDate = new ArrayList<String>();
    ArrayList<String> characterName = new ArrayList<String>();
    ArrayList<String> characterType = new ArrayList<String>();
    ArrayList<Integer> level = new ArrayList<Integer>();
    ArrayList<Integer> experiencePoints = new ArrayList<Integer>();
    ArrayList<Integer> maxHealth = new ArrayList<Integer>();
    ArrayList<Integer> health = new ArrayList<Integer>();
    ArrayList<Integer> attackinScore = new ArrayList<Integer>();
    ArrayList<Integer> defenceScore = new ArrayList<Integer>();
    ArrayList<Integer> stealthScore = new ArrayList<Integer>();
    ArrayList<Integer> manaScore = new ArrayList<Integer>();
    ArrayList<Float> moneyBank = new ArrayList<Float>();
    ArrayList<Float> moneyWallet = new ArrayList<Float>();

    ArrayList<String> battleDate = new ArrayList<String>();
    ArrayList<Integer> battleNo = new ArrayList<Integer>();
    ArrayList<String> attacker = new ArrayList<String>();
    ArrayList<String> defender = new ArrayList<String>();
    ArrayList<String> weapon = new ArrayList<String>();
    ArrayList<String> result = new ArrayList<String>();
    ArrayList<Integer> damage = new ArrayList<Integer>();

    ArrayList<String> character = new ArrayList<String>();
    ArrayList<String> item = new ArrayList<String>();
    ArrayList<String> itemType = new ArrayList<String>();
    ArrayList<String> weaponType = new ArrayList<String>();
    ArrayList<Integer> range = new ArrayList<Integer>();
    ArrayList<Float> price = new ArrayList<Float>();
    ArrayList<Integer> quantity = new ArrayList<Integer>();
    ArrayList<Integer> defendScore = new ArrayList<Integer>();
    ArrayList<Integer> attackScore = new ArrayList<Integer>();
    ArrayList<Integer> healingScore = new ArrayList<Integer>();
    ArrayList<Integer> manaScoreI = new ArrayList<Integer>();
    ArrayList<Integer> singleUse = new ArrayList<Integer>();
    ArrayList<Integer> wearable = new ArrayList<Integer>();
    ArrayList<Integer> worn = new ArrayList<Integer>();
    ArrayList<String> bodyPart = new ArrayList<String>();
    ArrayList<Integer> equipped = new ArrayList<Integer>();

    ArrayList<Integer> postionInt = new ArrayList<Integer>();
    ArrayList<Integer> postionFloat = new ArrayList<Integer>();
    ArrayList<ArrayList<Integer> > aListInt = new ArrayList<ArrayList<Integer>>();
    ArrayList<ArrayList<Float> > aListFloat = new ArrayList<ArrayList<Float>>();
    String line = "\r";  
    String splitBy = ",";
    int counter = 0;
    public Connection connect()
    {
        Connection conn = null;
        Scanner getDBNameObj = new Scanner(System.in);
        try {
            String url = "jdbc:sqlite:Coursework.db";
            conn = DriverManager.getConnection(url);
            System.out.println("Connection to SQLite has been established.");
        } 
        catch (SQLException e) 
        {
            System.out.println(e.getMessage());
        } 
        return conn;
    }
    public String fixApostrophe(String fixString)
    {
        if(fixString == null)
        {
            return null;
        }
        int index = fixString.indexOf("'");
        while (index >= 0 || index == fixString.length()) 
        {
            fixString = fixString.substring(0, index) + "'" + fixString.substring(index);
            index = fixString.indexOf("'", index + 2);
        }
        fixString = addQuotes(fixString);
        return fixString;
    }
    public String addQuotes(String fixString)
    {
        if(fixString == null)
        {
            return null;
        }
        fixString = "'" + fixString + "'";
        return fixString;
    }
    public void readCustomer()
    {
        counter = 0;
        try{  
            BufferedReader br = new BufferedReader(new FileReader("Customers.csv")); 
            aListInt.add(accountNumber);
            postionInt.add(0); 
            aListInt.add(level);
            postionInt.add(8);
            aListInt.add(experiencePoints);
            postionInt.add(9);
            aListInt.add(maxHealth);
            postionInt.add(10);
            aListInt.add(health);
            postionInt.add(11);
            aListInt.add(attackinScore);
            postionInt.add(12);
            aListInt.add(defenceScore);
            postionInt.add(13);
            aListInt.add(stealthScore);
            postionInt.add(14);
            aListInt.add(manaScore);
            postionInt.add(15);
            aListFloat.add(moneyBank);
            postionFloat.add(16);
            aListFloat.add(moneyWallet);
            postionFloat.add(17);
            while ((line = br.readLine()) != null) 
            {  
                String[] customers = line.split(splitBy,-1); 
                for(int i = 0; i < customers.length; i++)
                {
                    if(customers[i] == "")
                    {
                        customers[i] = null;
                    }
                }
                if(counter > 0)
                {  
                    try{
                        forename.add(customers[1]);
                        forename.set(forename.size() - 1, fixApostrophe(forename.get(forename.size() - 1)));
                        surname.add(customers[2]);
                        surname.set(surname.size() - 1, fixApostrophe(surname.get(surname.size() - 1)));
                        emailAddress.add(customers[3]);
                        emailAddress.set(emailAddress.size() - 1, fixApostrophe(emailAddress.get(emailAddress.size() - 1)));
                        characterCreationDate.add(customers[4]);
                        characterCreationDate.set(characterCreationDate.size() - 1, addQuotes(characterCreationDate.get(characterCreationDate.size() - 1)));
                        characterExpiraryDate.add(customers[5]);
                        characterExpiraryDate.set(characterExpiraryDate.size() - 1, addQuotes(characterExpiraryDate.get(characterExpiraryDate.size() - 1)));
                        characterName.add(customers[6]);
                        characterName.set(characterName.size() - 1, fixApostrophe(characterName.get(characterName.size() - 1)));
                        characterType.add(customers[7]);
                        characterType.set(characterType.size() - 1, fixApostrophe(characterType.get(characterType.size() - 1)));
                        for(int i = 0;i<postionInt.size();i++)
                        {
                            if(customers[postionInt.get(i)] == null)
                            {
                                aListInt.get(i).add(null);
                            }
                            else
                            {
                                aListInt.get(i).add(Integer.parseInt(customers[postionInt.get(i)]));
                            }
                        }
                        for(int i = 0;i<postionFloat.size();i++)
                        {
                            if(customers[postionFloat.get(i)] == null)
                            {
                                aListFloat.get(i).add(null);
                            }
                            else
                            {
                                aListFloat.get(i).add(Float.parseFloat(customers[postionInt.get(i)]));
                            }
                        }
                        // System.out.println("| " + accountNumber.get(counter-1)+" | " + forename.get(counter-1)+" | " + surname.get(counter-1)+" | " + emailAddress.get(counter-1)+" | " 
                        // + characterCreationDate.get(counter-1)+" | " + characterExpiraryDate.get(counter-1)+" | " + characterName.get(counter-1)+" | " +characterType.get(counter-1) 
                        // + " | "+ level.get(counter-1)+ " | " + experiencePoints.get(counter-1)+ " | " + maxHealth.get(counter-1)+ " | " + health.get(counter-1)+ "| " + attackinScore.get(counter-1)+ " | " 
                        // + defenceScore.get(counter-1)+ " | " + stealthScore.get(counter-1)+ " | " + manaScore.get(counter-1)+ " | " + moneyBank.get(counter-1)+ " | " + moneyWallet.get(counter-1)+" |");
                    }
                    catch (NumberFormatException ex){
                        ex.printStackTrace();
                    }
                }  
                counter = counter + 1;
            }
        }   
        catch (IOException e)   {  
            e.printStackTrace();  
        }  
        postionInt.removeAll(postionInt);
        postionFloat.removeAll(postionFloat);
        aListFloat.removeAll(aListFloat);
        aListInt.removeAll(aListInt);
    }
    public void readCombat()
    {
        counter = 0;
        try{
            BufferedReader br = new BufferedReader(new FileReader("Combat.csv")); 
            aListInt.add(battleNo);
            postionInt.add(1);
            aListInt.add(damage);
            postionInt.add(6);
            while ((line = br.readLine()) != null)   //returns a Boolean value  
            {  
                String[] combat = line.split(splitBy); 
                for(int i = 0; i < combat.length; i++)
                {
                    if(combat[i] == "")
                    {
                        combat[i] = null;
                    }
                }
                if(counter > 0)
                {
                    
                    try{
                        battleDate.add(combat[0]);
                        battleDate.set(battleDate.size() - 1, addQuotes(battleDate.get(battleDate.size() - 1)));
                        attacker.add(combat[2]);
                        attacker.set(attacker.size() - 1, fixApostrophe(attacker.get(attacker.size() - 1)));
                        defender.add(combat[3]);
                        defender.set(defender.size() - 1, fixApostrophe(defender.get(defender.size() - 1)));
                        weapon.add(combat[4]);
                        weapon.set(weapon.size() - 1, fixApostrophe(weapon.get(weapon.size() - 1)));
                        result.add(combat[5]);
                        result.set(result.size() - 1, fixApostrophe(result.get(result.size() - 1)));
                        for(int i = 0;i<postionInt.size();i++)
                        {
                            if(combat[postionInt.get(i)] == null)
                            {
                                aListInt.get(i).add(null);
                            }
                            else
                            {
                                aListInt.get(i).add(Integer.parseInt(combat[postionInt.get(i)]));
                            }
                        }
                        // System.out.println("| "+battleDate.get(counter-1) + " | " + battleNo.get(counter-1) + " | " + attacker.get(counter-1) + " | " + defender.get(counter-1)
                        // + " | " + defender.get(counter-1) + " | " + weapon.get(counter-1) + " | " + result.get(counter-1) + " | " + damage.get(counter-1) + " |");
                    }
                    catch (NumberFormatException ex){
                        // ex.printStackTrace();
                    }
                }
                counter = counter + 1;
            }
        }
        catch (IOException e)   {  
            e.printStackTrace();  
        }
        postionInt.removeAll(postionInt);
        postionFloat.removeAll(postionFloat);
        aListFloat.removeAll(aListFloat);
        aListInt.removeAll(aListInt);
    }
    public void readItems()
    {
        counter = 0;
        try{
            BufferedReader br = new BufferedReader(new FileReader("Items.csv")); 
            aListInt.add(range);
            postionInt.add(4);
            aListFloat.add(price);
            postionFloat.add(5);
            aListInt.add(quantity);
            postionInt.add(6);
            aListInt.add(defendScore);
            postionInt.add(7);
            aListInt.add(attackScore);
            postionInt.add(8);
            aListInt.add(healingScore);
            postionInt.add(9);
            aListInt.add(manaScoreI);
            postionInt.add(10);
            aListInt.add(singleUse);
            postionInt.add(11);
            aListInt.add(wearable);
            postionInt.add(12);
            aListInt.add(worn);
            postionInt.add(13);
            aListInt.add(equipped);
            postionInt.add(15);
            while ((line = br.readLine()) != null)  
            { 
                String[] items = line.split(splitBy,-1); 
                for(int i = 0; i < items.length; i++)
                {
                    if(items[i] == "")
                    {
                        items[i] = null;
                    }
                }
                if(counter > 0)
                {              
                    try{
                        character.add(items[0]);
                        character.set(character.size() - 1, fixApostrophe(character.get(character.size() - 1)));
                        item.add(items[1]);
                        item.set(item.size() - 1, fixApostrophe(item.get(item.size() - 1)));
                        itemType.add(items[2]);
                        itemType.set(itemType.size() - 1, fixApostrophe(itemType.get(itemType.size() - 1)));
                        weaponType.add(items[3]);
                        weaponType.set(weaponType.size() - 1, fixApostrophe(weaponType.get(weaponType.size() - 1)));
                        bodyPart.add(items[14]);
                        bodyPart.set(bodyPart.size() - 1, fixApostrophe(bodyPart.get(bodyPart.size() - 1)));
                        for(int i = 0;i<postionInt.size();i++)
                        {
                            if(items[postionInt.get(i)] == null)
                            {
                                aListInt.get(i).add(null);
                            }
                            else
                            {
                                aListInt.get(i).add(Integer.parseInt(items[postionInt.get(i)]));
                            }
                        }
                        for(int i = 0;i<postionFloat.size();i++)
                        {
                            if(items[postionFloat.get(i)] == null)
                            {
                                aListFloat.get(i).add(null);
                            }
                            else
                            {
                                aListFloat.get(i).add(Float.parseFloat(items[postionFloat.get(i)]));
                            }
                        }
                        // System.out.println("| " + character.get(counter-1) + " | " + item.get(counter-1) + " | " + itemType.get(counter-1) + " | " + weaponType.get(counter-1)
                        // + " | " + range.get(counter-1) + " | " + quantity.get(counter-1) + " | " + defendScore.get(counter-1) + " | " + attackScore.get(counter-1)
                        // + " | " + healingScore.get(counter-1) + " | " + manaScoreI.get(counter-1) + " | " + singleUse.get(counter-1) + " | " + wearable.get(counter-1) + " | " + worn.get(counter-1)
                        // + " | " + bodyPart.get(counter-1) + " | " + equipped.get(counter-1) + " |");
                    }
                    catch (NumberFormatException ex){
                        ex.printStackTrace();
                    }
                }
                counter = counter + 1;
            }
        }
        catch (IOException e){  
            e.printStackTrace();  
        }
        postionInt.removeAll(postionInt);
        postionFloat.removeAll(postionFloat);
        aListFloat.removeAll(aListFloat);
        aListInt.removeAll(aListInt);
    }
    public void createTable() 
	{
		Connection conn = null;
		Scanner getDBNameObj = new Scanner(System.in);
        try{
            conn = this.connect();
            Statement createPlayer = conn.createStatement();
            try{
                createPlayer.executeUpdate(
                "CREATE TABLE Player (Account_Number INTEGER NOT NULL, Forename TEXT, surname TEXT, email_Address TEXT, PRIMARY KEY(Account_Number));");
            }
            catch(SQLException sqe){
                System.out.println("Message = " + sqe.getMessage());
            }   
            Statement createHasCharacter = conn.createStatement();
            try{
                createHasCharacter.executeUpdate(
                "CREATE TABLE HasCharacter (Account_Number INTEGER NOT NULL, Character_Creation_Date DATE, Character_Expirary_Date DATE, Character_Name TEXT NOT NULL,"
                + "Character_Type TEXT, Level INTEGER, ExperiencePoints INTEGER, Max_Health INTEGER, Health INTEGER, AttackinScore INTEGER, DefenceScore INTEGER,"
                + "StealthScore INTEGER, ManaScore INTEGER, Money_bank FLOAT, Money_wallet FLOAT," 
                + "FOREIGN KEY (Account_Number) REFERENCES Player(Account_Number),PRIMARY KEY(Character_Name, Account_Number));");	
            }
            catch(SQLException sqe){
                System.out.println("Message = " + sqe.getMessage());
            }
            try{
                createPlayer.executeUpdate("ALTER TABLE Player RENAME TO Player_Old");
            }
            catch(SQLException sqe){
                System.out.println("Message = " + sqe.getMessage());
            }
            try{
                createPlayer.executeUpdate(
                "CREATE TABLE Player (Account_Number INTEGER NOT NULL, Character_Name TEXT NOT NULL,Forename TEXT, surname TEXT, email_Address TEXT, FOREIGN KEY (Character_Name) REFERENCES HasCharacter(Character_Name),PRIMARY KEY(Account_Number));");
            }
            catch(SQLException sqe){
                System.out.println("Message = " + sqe.getMessage());
            }
            try{
                createPlayer.executeUpdate(
                "DROP TABLE Player_Old;");
            }
            catch(SQLException sqe){
                System.out.println("Message = " + sqe.getMessage());
            }
            Statement createObject = conn.createStatement();
            try{
                createObject.executeUpdate(
                "CREATE TABLE Object (Item TEXT NOT NULL, Item_Type TEXT, WeaponType TEXT, Range INTEGER, Price FLOAT, "
                + "DefendScore INTEGER, AttackScore INTEGER, HealingScore INTEGER, ManaScore INTEGER, SingleUse BOOLEAN CHECK (SingleUse = 1 OR SingleUse = 0),"
                + "Wearable BOOLEAN CHECK (Wearable = 1 OR Wearable = 0), BodyPart TEXT, PRIMARY KEY(Item));");
            }
            catch(SQLException sqe){
                System.out.println("Message = " + sqe.getMessage());
            }
            Statement createContainsItemSpaceHas = conn.createStatement();
            try{
                createContainsItemSpaceHas.executeUpdate(
                "CREATE TABLE ContainsItemSpaceHas (Character TEXT NOT NULL, Item TEXT NOT NULL, Quantity INTEGER, Worn BOOLEAN CHECK (Worn = 1 OR Worn = 0), Equipped BOOLEAN CHECK (Equipped = 1 OR Equipped = 0), PRIMARY KEY (Character, Item) ,FOREIGN KEY (Character) REFERENCES HasCharacter(Character_Name),"
                +"FOREIGN KEY (Item) REFERENCES Object(Item));");
            }
            catch(SQLException sqe){
                System.out.println("Message = " + sqe.getMessage());
            }
            Statement createBattle = conn.createStatement();
            try{
                createBattle.executeUpdate(
                "CREATE TABLE BattleParticipate (BattleDate DATE, BattleNo INTEGER NOT NULL, Attacker TEXT NOT NULL, Defender TEXT NOT NULL, Weapon TEXT, Result TEXT, Damage INTEGER,"
                + "PRIMARY KEY (BattleNo), FOREIGN KEY (Attacker) REFERENCES HasCharacter(Character_Name), FOREIGN KEY (Defender) REFERENCES HasCharacter (Character_Name));");
            }
            catch(SQLException sqe){
                System.out.println("Message = " + sqe.getMessage());
            }
            Statement populatePlayer = conn.createStatement();
            for(int i = 0; i<accountNumber.size(); i++)
            {
                try{
                    populatePlayer.executeUpdate("INSERT INTO Player (Account_Number, Character_Name,Forename, surname, email_Address) VALUES (" + accountNumber.get(i) + ", " + characterName.get(i) + ", " + forename.get(i) + ", " + surname.get(i) + ", " + emailAddress.get(i) + ");");
                }
                catch(SQLException sqe){
                    System.out.println("Message = " + sqe.getMessage());
                }
            }
            for(int i = 0; i<characterName.size(); i++)
            {
                try{
                    populatePlayer.executeUpdate("INSERT INTO HasCharacter (Account_Number, Character_Creation_Date, Character_Expirary_Date, Character_Name, Character_Type,"
                    + "Level, ExperiencePoints, Max_Health, Health, AttackinScore, DefenceScore, StealthScore, ManaScore, Money_bank, Money_wallet)"
                    + "VALUES (" + accountNumber.get(i) + ", " + characterCreationDate.get(i) + ", " + characterExpiraryDate.get(i) + ", " + characterName.get(i) 
                    + ", " + characterType.get(i) + ", " + level.get(i) + ", " + experiencePoints.get(i) + ", " + maxHealth.get(i) + ", " + health.get(i)
                    + ", " + attackinScore.get(i) + ", " + defenceScore.get(i) + ", " + stealthScore.get(i) + ", " + manaScore.get(i) + ", " + moneyBank.get(i) + ", " + moneyWallet.get(i) + ");");
                }
                catch(SQLException sqe){
                    System.out.println("Message = " + sqe.getMessage());
                }
            }  
            for(int i = 0; i<item.size(); i++)
            {
                try{
                    populatePlayer.executeUpdate("INSERT INTO Object (Item, Item_Type, WeaponType, Range, Price,"
                    + " DefendScore, AttackScore, HealingScore, ManaScore, SingleUse, Wearable, BodyPart)"
                    + "VALUES (" + item.get(i) + ", " + itemType.get(i) + ", " + weaponType.get(i) + ", " + range.get(i) 
                    + ", " + price.get(i) + ", " +  defendScore.get(i) + ", " + attackScore.get(i)
                    + ", " + healingScore.get(i) + ", " + manaScoreI.get(i) + ", " + singleUse.get(i) + ", " + wearable.get(i)
                    + ", " + bodyPart.get(i) + ");");
                }
                catch(SQLException sqe){
                    System.out.println("Message = " + sqe.getMessage());
                }
            }
            for(int i = 0; i<battleNo.size(); i++)
            {
                try{
                    createBattle.executeUpdate("INSERT INTO BattleParticipate (BattleDate, BattleNo, Attacker, Defender, Weapon, Result, Damage)"
                    + "VALUES (" + battleDate.get(i) + ", "+ battleNo.get(i) + ", " + attacker.get(i) + ", " + defender.get(i) + ", "+ weapon.get(i) + ", " + result.get(i) + ", " + damage.get(i) + ");");
                }
                catch(SQLException sqe){
                    System.out.println("Message = " + sqe.getMessage());
                }
            }
            for(int i = 0; i<character.size(); i++)
            {
                try{
                    createContainsItemSpaceHas.executeUpdate("INSERT INTO ContainsItemSpaceHas (Character, Item, Quantity, Worn, Equipped)"
                    + "VALUES (" + character.get(i) + ", " + item.get(i) + ", " + quantity.get(i) + ", " + worn.get(i) + ", " + equipped.get(i) + ");");
                }
                catch(SQLException sqe){
                    System.out.println("Message = " + sqe.getMessage());
                }
            }
            try{
                System.out.println("SELECT Character_Name FROM HasCharacter INNER JOIN BattleParticipate ON HasCharacter.Character_Name = BattleParticipate.Attacker WHERE Result = 'Hit' OR Result = 'Victory' GROUP BY Character_Name ORDER BY COUNT(Result) DESC LIMIT 5");
                ResultSet rs = createContainsItemSpaceHas.executeQuery("SELECT Character_Name FROM HasCharacter INNER JOIN BattleParticipate ON HasCharacter.Character_Name = BattleParticipate.Attacker WHERE Result = 'Hit' OR Result = 'Victory' GROUP BY Character_Name ORDER BY COUNT(Result) DESC LIMIT 5");
                while(rs.next())
                {
                    System.out.println(rs.getString("Character_Name"));
                }
                System.out.println("Query is: SELECT Character_Name, COUNT(Result) AS TotalAttacks FROM HasCharacter INNER JOIN BattleParticipate ON HasCharacter.Character_Name = BattleParticipate.Attacker GROUP BY Character_Name HAVING COUNT(Result) > 5");
                rs = createContainsItemSpaceHas.executeQuery("SELECT Character_Name, COUNT(Result) AS TotalAttacks FROM HasCharacter INNER JOIN BattleParticipate ON HasCharacter.Character_Name = BattleParticipate.Attacker GROUP BY Character_Name HAVING COUNT(Result) > 5");
                while(rs.next())
                {
                    System.out.println(rs.getString("Character_Name") + ", " + rs.getInt("TotalAttacks"));
                }
                System.out.println("Query is: SELECT Character_Name FROM HasCharacter INNER JOIN BattleParticipate ON HasCharacter.Character_Name = BattleParticipate.Attacker GROUP BY Character_Name ORDER BY COUNT(Result) DESC");
                rs = createContainsItemSpaceHas.executeQuery("SELECT Character_Name FROM HasCharacter INNER JOIN BattleParticipate ON HasCharacter.Character_Name = BattleParticipate.Attacker GROUP BY Character_Name ORDER BY COUNT(Result) DESC");
                while(rs.next())
                {
                    System.out.println(rs.getString("Character_Name"));
                }
                System.out.println("Query is: SELECT Forename, Surname FROM Player INNER JOIN HasCharacter ON Player.Account_Number = HasCharacter.Account_Number GROUP BY Player.Account_Number HAVING COUNT(Player.Account_Number) > 4");
                rs = createContainsItemSpaceHas.executeQuery("SELECT Forename, Surname FROM Player INNER JOIN HasCharacter ON Player.Account_Number = HasCharacter.Account_Number GROUP BY Player.Account_Number HAVING COUNT(Player.Account_Number) > 4");
                while(rs.next())
                {
                    System.out.println(rs.getString("Forename") + ", " + rs.getString("Surname"));
                }
                System.out.println("Query is: SELECT Weapon FROM BattleParticipate GROUP BY Weapon HAVING COUNT(DISTINCT Attacker) > 9");
                rs = createContainsItemSpaceHas.executeQuery("SELECT Weapon FROM BattleParticipate GROUP BY Weapon HAVING COUNT(DISTINCT Attacker) > 9");
                while(rs.next())
                {
                    System.out.println(rs.getString("Weapon"));
                }
            }
            catch(SQLException sqe){
                System.out.println("Error Code = " + sqe.getErrorCode());
                System.out.println("SQL state = " + sqe.getSQLState());
                System.out.println("Message = " + sqe.getMessage());
                System.out.println("printTrace /n");
            }
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        finally { 
            try {
                if (conn != null) {
                    conn.close();
                }
            } 
            catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
	}
    public static void main(String[] args) 
    {
        Connect connect = new Connect();
        connect.readCustomer();
        connect.readCombat();
        connect.readItems();
        connect.createTable();
	}
}