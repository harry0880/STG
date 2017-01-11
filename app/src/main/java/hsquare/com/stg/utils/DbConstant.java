package hsquare.com.stg.utils;

/**
 * Created by harpreetsingh on 10/01/17.
 */

 interface DbConstant {
    
     int DBversion=1;
     String DBName="STG";
    
    
     String Tbl_Diseases="TblDiseases";
     String C_Diseases_ID="C_Diseases_ID";
     String C_Diseases_Details="C_Diseases_Details";
    
     String Tbl_DiseasesHeader="T_DiseasesHeader";
     String C_DiseasesHeader_ID="C_DiseasesHeader_ID";
     String C_DiseasesHeader_Details="C_DiseasesHeader_Details";
    
     String Tbl_DiseasesCaegory="T_DiseasesCaegory";
     String C_DiseasesCategory_ID="C_DiseasesCategory_ID";
     String C_DiseasesCategory_Details="C_DiseasesCategory_Details";
    
     String Create_Table_Diseases="Create Table "+Tbl_Diseases+" ("+C_Diseases_ID+" TEXT,"
            +C_Diseases_Details+" TEXT)";

     String Create_Table_DiseasesHeader="Create Table "+Tbl_DiseasesHeader+" ("+C_DiseasesHeader_ID+" TEXT,"
            +C_DiseasesHeader_Details+" TEXT)";

     String Create_Table_DiseasesCatgory="Create Table "+Tbl_DiseasesCaegory+" ("+C_DiseasesCategory_ID+" TEXT,"
            +C_DiseasesCategory_Details+" TEXT)";
    
    

    
    
}
