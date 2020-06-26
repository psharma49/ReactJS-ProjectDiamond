package diamond.springboot.db.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import diamond.springboot.db.entity.User.Feature;
import diamond.springboot.db.entity.User.ProductAggre;
import diamond.springboot.db.mapper.FeatureRowMapper;
import diamond.springboot.db.mapper.ProductAggreRowMapper;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.Locale;
@Repository
public class FeatureDaoImpl {

	public FeatureDaoImpl(NamedParameterJdbcTemplate template) {  
        this.template = template;  
}  
NamedParameterJdbcTemplate template;  
	
public LinkedHashMap<String, List<Feature>> featureLevelView(int lobid, int portfolioid, int productid,int yearid, String quarterid) {
	MapSqlParameterSource params = new MapSqlParameterSource();
	params.addValue("LOB_ID", lobid);
	params.addValue("PORTFOLIO_ID", portfolioid);
    params.addValue("PRODUCT_ID", productid);
    params.addValue("YEAR_ID", yearid);
    params.addValue("QUARTER_ID", quarterid);
   /* return template.query( "SELECT F. FEATURE_NAME,  K.KPI_ID, SUB.KPI_SUBCATEGORY_NAME, F.BUSINESS_VALUE, F.UNIT_OF_MEASUREMENT\r\n" + 
    		"\r\n" + 
    		"FROM LOB L, PORTFOLIO P, PRODUCT_MASTER PM, KPI_MASTER K, KPI_SUBCATEGORY SUB, FEATURE F\r\n" + 
    		"\r\n" + 
    		"WHERE\r\n" + 
    		"\r\n" + 
    		"F.KPI_SUBCATEGORY_ID = SUB.KPI_SUBCATEGORY_ID\r\n" + 
    		"\r\n" + 
    		"AND F.KPI_ID = K.KPI_ID\r\n" + 
    		"\r\n" + 
    		"AND F.PRODUCT_ID = PM.PRODUCT_ID\r\n" + 
    		"\r\n" + 
    		"AND PM.PORTFOLIO_ID= P.PORTFOLIO_ID\r\n" + 
    		"\r\n" + 
    		"AND P.LOB_ID = L.LOB_ID\r\n" + 
    		"\r\n" + 
    		"AND L.LOB_STATUS = 'Y'\r\n" + 
    		"\r\n" + 
    		"AND P.PORTFOLIO_STATUS='Y'\r\n" + 
    		"\r\n" + 
    		"AND PM.PRODUCT_STATUS='Y'\r\n" + 
    		"\r\n" + 
    		"AND F.LOB_ID= :LOB_ID\r\n" + 
    		"\r\n" + 
    		"AND F.PORTFOLIO_ID = :PORTFOLIO_ID\r\n" + 
    		"\r\n" + 
    		"AND F.PRODUCT_ID = :PRODUCT_ID\r\n" + 
    		"\r\n" + 
    		"ORDER by  F.FEATURE_NAME, K.KPI_ID, SUB.KPI_SUBCATEGORY_NAME\r\n" + 
    		"", params,  new FeatureRowMapper());
    	*/
    String qs = "";
    	   List<Feature> lf = new ArrayList<Feature>();
    	   String fquery =  "SELECT  F. FEATURE_NAME,  K.KPI_NAME, F.BUSINESS_VALUE, F.UNIT_OF_MEASUREMENT\r\n" + 
       	  		"FROM LOB L, PORTFOLIO P, PRODUCT_MASTER PM, KPI_MASTER K, KPI_SUBCATEGORY SUB, FEATURE F\r\n" + 
       	  		"WHERE\r\n" + 
       	  		"F.KPI_SUBCATEGORY_ID = SUB.KPI_SUBCATEGORY_ID\r\n" + 
       	  		"AND F.KPI_ID = K.KPI_ID\r\n" + 
       	  		"AND F.PRODUCT_ID = PM.PRODUCT_ID\r\n" + 
       	  		"AND PM.PORTFOLIO_ID= P.PORTFOLIO_ID\r\n" + 
       	  		"AND P.LOB_ID = L.LOB_ID\r\n" + 
       	  		"AND L.LOB_STATUS = 'Y'\r\n" + 
       	  		"AND P.PORTFOLIO_STATUS='Y'\r\n" + 
       	  		"AND PM.PRODUCT_STATUS='Y'\r\n" + 
       	  		"AND F.LOB_ID= :LOB_ID\r\n" + 
       	  		"AND F.PORTFOLIO_ID = :PORTFOLIO_ID\r\n" + 
       	  		"AND F.PRODUCT_ID = :PRODUCT_ID\r\n" + 
       	  		"AND F.YEAR = :YEAR_ID\r\n" + 
           		"\r\n" ; 
       	  if(!quarterid.equals("Q")) {
       		  if(!quarterid.equals("YTD")) {
       		    fquery = fquery + "AND F.QUARTER  IN (:QUARTER_ID)\r\n";
       		  }
       		  else {
       			LocalDate currentDate = LocalDate.now();
       	      Calendar cal = Calendar.getInstance(Locale.US);
       	      int month = cal.get(Calendar.MONTH);
       	      int quarter = (month / 3) + 1;
       	      if(quarter == 1) {
       	    	 qs = qs + "('Q1')" ;
       	      }
       	      else if(quarter == 2) {
       	    	qs = qs + "('Q1', 'Q2')" ;
       	      }
       	      else if (quarter == 3) {
       	    	qs = qs + "('Q1','Q2', 'Q3')" ;
       	      }
       	      else  {
       	    	qs = qs + "('Q1','Q2','Q3','Q4')" ; 
       	      }
       	   fquery = fquery + "AND F.QUARTER  IN " + qs ;
       		  }
       		  
       		    }
       	  fquery = fquery + " ORDER by  F.FEATURE_NAME" ;
       	System.out.println(fquery);
    	  		
    	  lf = template.query(fquery,params,  new FeatureRowMapper());		
    			  
 LinkedHashMap<String, List<Feature>> feature_map = new LinkedHashMap<>(); 
      String temp1 = "";
     
      
     
      for(int j = 0; j < lf.size(); j++){
      temp1 = "";
      List<Feature> lf_temp = new ArrayList<Feature>(); 
      
      temp1 = lf.get(j).getFeature_name();
      while(j+1 < lf.size() &&  lf.get(j+1).getFeature_name().equals(temp1)){
    	 
      lf_temp.add(lf.get(j));
     
      // li_temp.add( new ProductAggre( li.get(i).getKpi_name(),li.get(i).getKpi_subcategory_name(), li.get(i).getUnit_of_measurement(), li.get(i).getSum(),li.get(i).getAvg())); 
       
       
      j++;
      }
      // li_temp.add( new ProductAggre( li.get(i).getKpi_name(),li.get(i).getKpi_subcategory_name(), li.get(i).getUnit_of_measurement(), li.get(i).getSum(),li.get(i).getAvg()));  
       
      lf_temp.add( lf.get(j));
      
      feature_map.put( temp1, lf_temp);
          // li_temp.clear();
      }
    	return feature_map; 	 
    	 
    		
}

}
