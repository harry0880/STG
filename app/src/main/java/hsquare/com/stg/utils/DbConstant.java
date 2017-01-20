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
    
     String Tbl_DiseasesCategory="T_DiseasesCaegory";
     String C_DiseasesCategory_ID="C_DiseasesCategory_ID";
     String C_DiseasesCategory_Details="C_DiseasesCategory_Details";

     String Tbl_SubDiseaseid="T_SubDiseaseid";
    String C_SubDiseaseid="C_SubDiseaseid";
    String C_SubDiseaseDetail="C_SubDiseaseDetail";

    String Tbl_DiseasesContainer="TblDiseasecontainer";
    String C_diseaseheaddetail_content="C_diseaseheaddetail_content";

    

    String Create_Table_Diseasecontainer="Create Table "+Tbl_DiseasesContainer+" ("+C_DiseasesCategory_ID+" TEXT,"
            +C_DiseasesCategory_Details+" TEXT,"
            +C_Diseases_ID+" TEXT,"
            +C_Diseases_Details+" TEXT,"
            +C_SubDiseaseid+" TEXT,"
            +C_SubDiseaseDetail+" TEXT,"
            +C_diseaseheaddetail_content+" TEXT)";
    

    
    
}
