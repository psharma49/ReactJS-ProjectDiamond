package diamond.springboot.db.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import diamond.springboot.db.entity.User.Avg;
import diamond.springboot.db.entity.User.Display;
import diamond.springboot.db.entity.User.Feature;
import diamond.springboot.db.entity.User.Featureq;
import diamond.springboot.db.entity.User.Lob;
import diamond.springboot.db.entity.User.Portfolio;
import diamond.springboot.db.entity.User.PortfolioIndi;
import diamond.springboot.db.entity.User.Product;
import diamond.springboot.db.entity.User.ProductAggre;
import diamond.springboot.db.entity.User.ProductsGraph;
import diamond.springboot.db.entity.User.ResponseMessage;
import diamond.springboot.db.entity.User.User;
import diamond.springboot.db.entity.User.Year;
import diamond.springboot.db.service.UserService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class UserController {
	
@Autowired
 private UserService userService;

@RequestMapping("/users/{userid}/{pswd}")

public ResponseEntity<Void>checkUser(@PathVariable int userid, @PathVariable String pswd){
	 if(userService.checkUser(userid,pswd)==true)
	 {
	return ResponseEntity.status(HttpStatus.OK).build();
	 }
	 else
	 {
	return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
	 }
	}

@RequestMapping("/viewdash_board/year")
public List<Year> findAllYear(){
	return userService.findAllYear();
}

  @RequestMapping("/viewdash_board/lob/yearid")
  public List<Lob> findAllLob() {	
	return userService.findAllLob();
}	

@RequestMapping("/viewdash_board/portfolio/{lobid}/yearid")
  public List<Portfolio> findAllPortfolio(@PathVariable int lobid) {	
	return userService.findAllPortfolio(lobid);
}	

@RequestMapping("/viewdash_board/product/{portfolioid}/yearid")
  public List<Product> findAllProduct(@PathVariable int portfolioid) {	
	return userService.findAllProduct(portfolioid);
}	

@RequestMapping("/viewdash_board/product_aggregate_view/{lobid}/{portfolioid}/{productid}/{yearid}/{quarterid}")
public List<ProductAggre> bvProductAggre(@PathVariable int lobid, @PathVariable int portfolioid, @PathVariable int productid, @PathVariable int yearid, @PathVariable String quarterid ){
	return userService.bvProductAggre(lobid, portfolioid, productid, yearid, quarterid);
}

@RequestMapping("/viewdash_board/product_feature_view/{lobid}/{portfolioid}/{productid}/{yearid}/{quarterid}")
public LinkedHashMap<String, List<Feature>> featureLevelView(@PathVariable int lobid, @PathVariable int portfolioid, @PathVariable int productid, @PathVariable int yearid, @PathVariable String quarterid){
	return userService.featureLevelView(lobid, portfolioid, productid, yearid, quarterid);
	
}


@RequestMapping("/viewdash_board/product_avg_ttv_view/{lobid}/{portfolioid}/{productid}/{yearid}/{quarterid}")
 public List<Avg> findAvg(@PathVariable int lobid, @PathVariable int portfolioid, @PathVariable int productid,@PathVariable int yearid, @PathVariable String quarterid ){
  return userService.findAvg(lobid, portfolioid,productid, yearid, quarterid);
}

@RequestMapping("/viewdash_board/product_featureq_view/{lobid}/{portfolioid}/{productid}/{yearid}/{quarterid}")
public List<Featureq> featureqLevelView(@PathVariable int lobid, @PathVariable int portfolioid, @PathVariable int productid, @PathVariable int yearid, @PathVariable String quarterid){
	return userService.featureqLevelView(lobid, portfolioid, productid, yearid, quarterid);
	
}

@RequestMapping("/viewdash_board/product_display_view/{lobid}/{portfolioid}/{productid}/{yearid}/{quarterid}")
public List<Display> displayView(@PathVariable int lobid, @PathVariable int portfolioid, @PathVariable int productid, @PathVariable int yearid, @PathVariable String quarterid){
	return userService.displayView(lobid, portfolioid, productid, yearid, quarterid);
	
}

@RequestMapping("/viewdash_board/indi_portfolio_view/{lobid}/{portfolioid}/{yearid}/{quarterid}")
public List<PortfolioIndi> indiPortfolioView(@PathVariable int lobid, @PathVariable int portfolioid, @PathVariable int yearid, @PathVariable String quarterid){
	return userService.indiPortfolioView(lobid, portfolioid, yearid, quarterid);
	
}
@RequestMapping("/viewdash_board/portfolio_graph_view/{lobid}/{portfolioid}/{display_name}/{yearid}/{quarterid}")
public List<ProductsGraph> portfolioGraphView(@PathVariable int lobid, @PathVariable int portfolioid,@PathVariable String display_name, @PathVariable int yearid, @PathVariable String quarterid){
	return userService.portfolioGraphView(lobid, portfolioid, display_name, yearid, quarterid);
	
}

/*
@PostMapping("/upload")
public ResponseEntity<ResponseMessage> uploadExcel(@RequestParam("file") MultipartFile file) {
  String message = "";
if(userService.uploadexcel(file))
	 {
	message = "Uploaded the file successfully: " + file.getOriginalFilename();
      return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
	 }
	 else
	 {
	 message = "Upload unsuccessful, Please try again!";
  return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage(message));
	 }
	}
	*/

//@PostMapping("/upload")
//public ResponseEntity<ResponseMessage> readExcel(@RequestParam("file") MultipartFile file) throws IOException  {
//  return userService.readExcel(file);
//}
	
@PostMapping("/upload")
public String readExcel(@RequestParam("file") MultipartFile file) throws IOException  {
  return userService.readExcel(file);
}

	

}
