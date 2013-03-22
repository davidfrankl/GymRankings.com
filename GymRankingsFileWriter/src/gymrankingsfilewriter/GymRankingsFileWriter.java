/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gymrankingsfilewriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import parsetools.ParseTools;

/**
 *
 * @author davidfrankl
 */
public class GymRankingsFileWriter {

    /**
     * @param args the command line arguments
     */
    
    String myFile;
    
    static ArrayList<String> menIndivFiles;
    static ArrayList<String> menTeamFiles;
    static ArrayList<String> womenIndivFiles;
    static ArrayList<String> womenTeamFiles;
    
    public GymRankingsFileWriter(String fileName) throws FileNotFoundException, IOException{
        System.out.println("Working on "+fileName);
        myFile = ParseTools.getText(fileName);
        
    }
    
    public static void main(String[] args) throws FileNotFoundException, IOException {
        menTeamFiles = new ArrayList();
        menTeamFiles.add("m_team_aa.txt");
        menTeamFiles.add("m_team_fx.txt");
        menTeamFiles.add("m_team_ph.txt");
        menTeamFiles.add("m_team_sr.txt");
        menTeamFiles.add("m_team_lv.txt");
        menTeamFiles.add("m_team_pb.txt");
        menTeamFiles.add("m_team_hb.txt");

        menIndivFiles = new ArrayList();
        menIndivFiles.add("m_indiv_aa.txt");
        menIndivFiles.add("m_indiv_fx.txt");
        menIndivFiles.add("m_indiv_ph.txt");
        menIndivFiles.add("m_indiv_sr.txt");
        menIndivFiles.add("m_indiv_lv.txt");
        menIndivFiles.add("m_indiv_pb.txt");
        menIndivFiles.add("m_indiv_hb.txt");

        womenTeamFiles = new ArrayList();
        womenTeamFiles.add("w_team_aa.txt");
        womenTeamFiles.add("w_team_v.txt");
        womenTeamFiles.add("w_team_ub.txt");
        womenTeamFiles.add("w_team_bb.txt");
        womenTeamFiles.add("w_team_fx.txt");

        womenIndivFiles = new ArrayList();
        womenIndivFiles.add("w_indiv_aa.txt");
        womenIndivFiles.add("w_indiv_v.txt");
        womenIndivFiles.add("w_indiv_ub.txt");
        womenIndivFiles.add("w_indiv_bb.txt");
        womenIndivFiles.add("w_indiv_fx.txt");
                
        //script: update old tables
        //script: pull new data
        
        //convertInputToTables(true, true);
        //convertInputToTables(true, false);
        //convertInputToTables(false, true);
        //convertInputToTables(false, false);
        
        String menDate = "March 18, 2013"; 
        String womenDate = "March 18, 2013";
        
        //update(menDate, true, true);
        //update(menDate, true, false);
       
        //update(womenDate, false, true);
        //update(womenDate, false, false);
        
        //push 
    }
    
    private static String getPathName(boolean isMen, boolean isTeam){
        
        String pathName = "tables/";
        
        //
        //String pathName = "old_tables/";
        
        if(isMen) pathName+="men/";
        else pathName+="women/";
        
        
        if(isTeam) pathName+="team/";
        else pathName+="indiv/";
        
        
        return pathName;
    }
    
    private static void convertInputToTables(boolean isMen, boolean isTeam) throws FileNotFoundException, IOException{
        
        String outputPath = getPathName(isMen, isTeam);
        System.out.println("Converting input to table: "+outputPath);
        
        String inputFolder = "input";
        
        //
        //String inputFolder = "old_tables";
        
        ArrayList<String> inputFiles;
        
        if(isMen){
            if(isTeam) inputFiles = menTeamFiles;
            else inputFiles = menIndivFiles;
        }
        else{
            if(isTeam) inputFiles = womenTeamFiles;
            else inputFiles = womenIndivFiles;
        }
        
        for(int i=0; i<inputFiles.size(); i++){
            String fileName = inputFiles.get(i);
            
            ParseTools.writeToFile(inputFolder+"/"+fileName, outputPath+fileName);
        }
        
        
    }
    
    private static ArrayList<String> getClassNames(boolean isMen, boolean isTeam){
        
        ArrayList<String> classNames = new ArrayList();
        
        String className = "";
        
        if(isMen){
            className+="men ";
            
            String[] mensEvents = {"t", "fx", "ph", "sr", "v", "pb", "hb"};
            
            if(isTeam){
                className+="team ";
            }
            else{
                className+="individual ";
                mensEvents[0]="aa";
            }
            for(int i=0; i<mensEvents.length; i++){
                System.out.println("className: "+className+mensEvents[i]);
                    classNames.add(className+mensEvents[i]);
            }
        }
        else{
            className+="women ";
            
            String[] womensEvents = {"t", "v", "ub", "bb", "fx"};
        
            if(isTeam){
                className+="team ";
            }
            else{
                className+="individual ";
                womensEvents[0]="aa";
            }
            for(int i=0; i<womensEvents.length; i++){
                    classNames.add(className+womensEvents[i]);
            }
        }
        return classNames;
        
    }
    
    private static void update(String date, boolean isMen, boolean isTeam) throws FileNotFoundException, IOException{
        
        String fileName;
        if(isMen) {
            if(isTeam) fileName="index.html";
            else fileName="menIndiv.html";
        }
        else{
            if(isTeam) fileName="womenTeam.html";
            else fileName="womenIndiv.html";
        }
        
        
        
        System.out.println("Updating "+fileName);
        
        String templatePathName="templates/"+fileName;
        
        fileName = "indexTest.html";
        
        GymRankingsFileWriter writer = new GymRankingsFileWriter(templatePathName);
        
        String tablePathName = getPathName(isMen, isTeam);
        String oldTablePathName = "old_"+tablePathName;
        
        ArrayList<String> inputFiles;
        
        if(isMen){
            if(isTeam){
                inputFiles = menTeamFiles;
            }
            else{
                inputFiles = menIndivFiles;
            }
        }
        else{
            if(isTeam){
                inputFiles = womenTeamFiles;
            }
            else{
                inputFiles = womenIndivFiles;
            }
        }
        
        ArrayList<String> classNames = getClassNames(isMen, isTeam);
        
        for(int i=0; i<inputFiles.size(); i++){
            
            String table = ParseTools.getText(tablePathName+inputFiles.get(i));
            String oldTable = ParseTools.getText(oldTablePathName+inputFiles.get(i));
            
            table = ParseTools.insertRankChange(table, oldTable, isMen, isTeam);
            //System.out.println(table);
            //System.out.println(classNames.get(i));
            writer.myFile = ParseTools.appendSpan(writer.myFile, table, "div", classNames.get(i), true);
        }
        
        Map<String, String> insertStrings = new HashMap<String, String>(); //from "tagName" to "attribute=\"value\""
        insertStrings.put("<table", " class=\"table-striped table-bordered table-condensed\"");
        insertStrings.put("s Updated ", date);
        writer.myFile = ParseTools.insertSpans(writer.myFile, insertStrings);
        
        ParseTools.writeToFile(writer.myFile, "output/"+fileName);
    }
}
