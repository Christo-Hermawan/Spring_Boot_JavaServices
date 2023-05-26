package demo.tesSwagger.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import demo.tesSwagger.model.userEntity;
import demo.tesSwagger.repository.UserRepository;


@RestController
@RequestMapping("/tes")
@CrossOrigin(origins = "http://localhost:8082")
public class getController {

    @Autowired
    private UserRepository userRepository;

    // Kode-kode dibawah dengan Store Procedure

    // store procedure di defined di class entity
    // Mendapatkan total data dari parameter utm_key = value
    @Transactional 
    @GetMapping("/countUtmProc")
    public ResponseEntity<?> countUtmProc
    (
        @RequestParam(required = false) String TransactionName, 
        @RequestParam(required = false) String utm_source, 
        @RequestParam(required = false) String utm_medium,
        @RequestParam(required = false) String utm_campaign,
        @RequestParam(required = false) String utm_content,
        @RequestParam(required = false) String utm_term,
        @RequestParam(required = false) String type
        ){
        try {
            TransactionName = "CountUtm";
            int count = userRepository.countUtmProc(TransactionName, utm_source, utm_medium, utm_campaign, utm_content, utm_term, type);

            if (count > 0 ){

                    if( utm_source != null && type != null){
                        String json = String.format("{\"Status\": \"200\", \"Message\": \"Success\", \"Result\": {\"type\": \"%s\", \"utm_source\": {\"%s\":\"%d\"}}}",type,utm_source,count);
                        return ResponseEntity.ok(json);

                    }else if ( utm_source != null && utm_term != null){
                        String json = String.format("{\"Status\": \"200\", \"Message\": \"Success\", \"Result\": {\"utm_source\": { \"%s\":\"%d\" \"utm_term\": \"%s\":\"%d\"}}}",utm_source, count, utm_term, count);
                        return ResponseEntity.ok(json);
              
                    }else if (utm_source != null){
                        String json = String.format("{\"Status\": \"200\", \"Message\": \"Success\", \"Result\": {\"utm_source\": { \"%s\":\"%d\"}}}",utm_source, count);
                        return ResponseEntity.ok(json);

                    }else if ( utm_medium != null && type != null){
                        String json = String.format("{\"Status\": \"200\", \"Message\": \"Success\", \"Result\": {\"type\": \"%s\", \"utm_medium\": {\"%s\":\"%d\"}}}",type,utm_medium,count);
                        return ResponseEntity.ok(json);

                    }else if (utm_medium != null){
                        String json = String.format("{\"Status\": \"200\", \"Message\": \"Success\", \"Result\": {\"utm_medium\": { \"%s\":\"%d\"}}}",utm_medium, count);
                        return ResponseEntity.ok(json);

                    }else if ( utm_campaign != null && type != null){
                        String json = String.format("{\"Status\": \"200\", \"Message\": \"Success\", \"Result\": {\"type\": \"%s\", \"utm_campaign\": {\"%s\":\"%d\"}}}",type,utm_campaign,count);
                        return ResponseEntity.ok(json);
       
                    }else if (utm_campaign != null){
                        String json = String.format("{\"Status\": \"200\", \"Message\": \"Success\", \"Result\": {\"utm_campaign\": { \"%s\":\"%d\"}}}",utm_campaign, count);
                        return ResponseEntity.ok(json);

                    }else if ( utm_content != null && type != null){
                        String json = String.format("{\"Status\": \"200\", \"Message\": \"Success\", \"Result\": {\"type\": \"%s\", \"utm_content\": {\"%s\":\"%d\"}}}",type,utm_content,count);
                        return ResponseEntity.ok(json);

                    }else if (utm_content != null){
                        String json = String.format("{\"Status\": \"200\", \"Message\": \"Success\", \"Result\": {\"utm_content\": { \"%s\":\"%d\"}}}",utm_content, count);
                        return ResponseEntity.ok(json);

                    }else if ( utm_term != null && type != null){
                        String json = String.format("{\"Status\": \"200\", \"Message\": \"Success\", \"Result\": {\"type\": \"%s\", \"utm_term\": {\"%s\":\"%d\"}}}",type,utm_term,count);
                        return ResponseEntity.ok(json);

                    }else if (utm_term != null){
                        String json = String.format("{\"Status\": \"200\", \"Message\": \"Success\", \"Result\": {\"utm_term\": { \"%s\":\"%d\"}}}",utm_term, count);
                        return ResponseEntity.ok(json);

                    }else if (type != null){
                        String json = String.format("{\"Status\": \"200\", \"Message\": \"Success\", \"Result\": {\"type\": { \"%s\":\"%d\"}}}",type, count);
                        return ResponseEntity.ok(json);
            
                    }else {
                        // Kalau key utm tidak ada 
                        String json = String.format("{Status: \"404\", \"Message\": \"Error\", \"Result\": {\"UTM not found\"}}");
                        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(json);
                    }
                  
            }else {
                // Kalau value dari key utm tidak ada
                String json = String.format("{\"Status\": \"404\", \"Message\": \"Error\", \"Result\": {\"UTM value not found\"}}");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(json);
            }
        
        } catch (Exception e) {
            String json = String.format("{\"Status\": \"Error\", \"Message\":  \":%s\"}", e.getMessage() );
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(json);

        }
    }

    // store procedure di defined di class entity
    // Mendapatkan list dari parameter type = value
    @Transactional
    @GetMapping("/getUtmList")
    public List<userEntity> getUtmList(
        @RequestParam(required = false) String TransactionName, 
        @RequestParam(required = false) String utm_source, 
        @RequestParam(required = false) String utm_medium, 
        @RequestParam(required = false) String utm_campaign, 
        @RequestParam(required = false) String utm_content, 
        @RequestParam(required = false) String utm_term, 
        @RequestParam(required = false) String type) {

        TransactionName = "ListUtm";

        return userRepository.getUtmList(TransactionName, utm_source, utm_medium, utm_campaign, utm_content, utm_term, type);
    }

    // store procedure di defined di class entity
    // Mendapatkan total data dari parameter type = value
    @Transactional 
    @GetMapping("/countTypeProc")
    public ResponseEntity<?> countTypeProc(@RequestParam(required = false) String TransactionName, @RequestParam(required = false) String type){
        try {
            TransactionName = "CountType";
            int count = userRepository.countTypeProc(TransactionName, type);

            if (count > 0 ){

       
                if ( type != null){
                String json = String.format("{\"Status\": \"200\", \"Message\": \"Success\", \"Result\": {\"type\": { \"%s\":\"%d\"}}}" ,type, count);
                return ResponseEntity.ok(json);
                }
               else {
                    String json = String.format("{\"Status\": \"404\", \"Message\": \"Error\", \"Result\": {\"value not found\"}}" );
                    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(json);
                }
            
            }else {
                // Kalau value dari key type tidak ada
                String json = String.format("{\"Status\": \"404\", \"Message\": \"Error\", \"Result\": {\"utm key not found\"}}");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(json);
            }

        } catch (Exception e) {
            String json = String.format("{\"Status\": \"Error\", \"Message\":  \":%s\"}", e.getMessage() );
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(json);

        }
    }
    
    // store procedure di defined di class entity
    // Mendapatkan list dari parameter type = value
    @Transactional
    @GetMapping("/getTypeList")
    public List<userEntity> getTypeList(
        @RequestParam(required = false) String Transactionalname,
        @RequestParam(required = false) String type) {

            Transactionalname = "ListType";
            return userRepository.getTypeList(Transactionalname, type);
        }

    // Nama store procedure langsung ditulis pada @Procedure di repository
    // Mendapatkan seluruh data yang ada
    @Transactional // dengan @Transactional ataupun tidak, masih bisa
    @GetMapping("/getAllData")
    public List<userEntity> getAllData(){
        return userRepository.getAllData();
    }

    }

//     Gagal dengan @Query di repository 
//     @Transactional
//     @GetMapping("/ProcQueryList")
//     public List<userEntity> ProcQueryList(@RequestParam(required = false) String utm_source){
//         return userRepository.ProcQueryList( utm_source);
// }
 



// Kode-kode dibawah dibuat tanpa store procedure

// @GetMapping("/getUtm")
// public ResponseEntity<?> getUtm2(@RequestParam(required = false) String utm_source,@RequestParam(required = false) String utm_medium,@RequestParam(required = false) String utm_campaign, @RequestParam(required = false) String utm_content, @RequestParam(required = false) String utm_term, @RequestParam(required = false) String type) {
//     try {
//         int count = userRepository.countUtm(utm_source, utm_medium, utm_campaign, utm_content, utm_term, type);

//         if (count > 0 ){

//                 if( utm_source != null && type != null){
//                     String json = String.format("{\"Status\": \"200\", \"Message\": \"Success\", \"Result\": {\"type\": \"%s\", \"utm_source\": {\"%s\":\"%d\"}}}",type,utm_source,count);
//                     return ResponseEntity.ok(json);

//                 }else if ( utm_source != null && utm_term != null){
//                     String json = String.format("{\"Status\": \"200\", \"Message\": \"Success\", \"Result\": {\"utm_source\": { \"%s\":\"%d\" \"utm_term\": \"%d\"}}}",utm_source, count, count);
//                     return ResponseEntity.ok(json);

//                 }else if (utm_source != null){
//                     String json = String.format("{\"Status\": \"200\", \"Message\": \"Success\", \"Result\": {\"utm_source\": { \"%s\":\"%d\"}}}",utm_source, count);
//                     return ResponseEntity.ok(json);

//                 }else if ( utm_medium != null && type != null){
//                     String json = String.format("{\"Status\": \"200\", \"Message\": \"Success\", \"Result\": {\"type\": \"%s\", \"utm_medium\": {\"%s\":\"%d\"}}}",type,utm_medium,count);
//                     return ResponseEntity.ok(json);

//                 }else if (utm_medium != null){
//                     String json = String.format("{\"Status\": \"200\", \"Message\": \"Success\", \"Result\": {\"utm_medium\": { \"%s\":\"%d\"}}}",utm_medium, count);
//                     return ResponseEntity.ok(json);

//                 }else if ( utm_campaign != null && type != null){
//                     String json = String.format("{\"Status\": \"200\", \"Message\": \"Success\", \"Result\": {\"type\": \"%s\", \"utm_campaign\": {\"%s\":\"%d\"}}}",type,utm_campaign,count);
//                     return ResponseEntity.ok(json);
   
//                 }else if (utm_campaign != null){
//                     String json = String.format("{\"Status\": \"200\", \"Message\": \"Success\", \"Result\": {\"utm_campaign\": { \"%s\":\"%d\"}}}",utm_campaign, count);
//                     return ResponseEntity.ok(json);

//                 }else if ( utm_content != null && type != null){
//                     String json = String.format("{\"Status\": \"200\", \"Message\": \"Success\", \"Result\": {\"type\": \"%s\", \"utm_content\": {\"%s\":\"%d\"}}}",type,utm_content,count);
//                     return ResponseEntity.ok(json);

//                 }else if (utm_content != null){
//                     String json = String.format("{\"Status\": \"200\", \"Message\": \"Success\", \"Result\": {\"utm_content\": { \"%s\":\"%d\"}}}",utm_content, count);
//                     return ResponseEntity.ok(json);

//                 }else if ( utm_term != null && type != null){
//                     String json = String.format("{\"Status\": \"200\", \"Message\": \"Success\", \"Result\": {\"type\": \"%s\", \"utm_term\": {\"%s\":\"%d\"}}}",type,utm_term,count);
//                     return ResponseEntity.ok(json);

//                 }else if (utm_term != null){
//                     String json = String.format("{\"Status\": \"200\", \"Message\": \"Success\", \"Result\": {\"utm_term\": { \"%s\":\"%d\"}}}",utm_term, count);
//                     return ResponseEntity.ok(json);

//                 }else if (type != null){
//                     String json = String.format("{\"Status\": \"200\", \"Message\": \"Success\", \"Result\": {\"type\": { \"%s\":\"%d\"}}}",type, count);
//                     return ResponseEntity.ok(json);
        
//                 }else {
//                     // Kalau key utm tidak ada 
//                     String json = String.format("{Status: \"404\", \"Message\": \"Error\", \"Result\": {\"UTM not found\"}}");
//                     return ResponseEntity.status(HttpStatus.NOT_FOUND).body(json);
//                 }
              
//         }else {
//             // Kalau value dari key utm tidak ada
//             String json = String.format("{\"Status\": \"404\", \"Message\": \"Error\", \"Result\": {\"UTM value not found\"}}");
//             return ResponseEntity.status(HttpStatus.NOT_FOUND).body(json);
//         }

    
//     } catch (Exception e) {
//         String json = String.format("{\"Status\": \"Error\", \"Message\":  \":%s\"}", e.getMessage() );
//         return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(json);

//     }
// }

// @GetMapping("/getType")
// public ResponseEntity<?> getType(@RequestParam String type){
//     try {
//         int count = userRepository.countByType(type);

//         if (count > 0 ){

      
//                 String json = String.format("{\"Status\": \"200\", \"Message\": \"Success\", \"Result\": {\"Type\": { \"%s\":\"%d\"}}}" ,type, count);
//                 return ResponseEntity.ok(json);
                
        
//         }else {
//             // Kalau value dari key type tidak ada
//             String json = String.format("{\"Status\": \"404\", \"Message\": \"Error\", \"Result\": {\"Type value not found\"}}");
//             return ResponseEntity.status(HttpStatus.NOT_FOUND).body(json);
//         }

    
//     } catch (Exception e) {
//         String json = String.format("{\"Status\": \"Error\", \"Message\":  \":%s\"}", e.getMessage() );
//         return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(json);

//     }
// }

// @GetMapping("/getUserByUtm")
// public List<userEntity> getUserByUtm(@RequestParam(required = false) String utm_source, @RequestParam(required = false) String utm_medium, @RequestParam(required = false) String utm_campaign, @RequestParam(required = false) String utm_content, @RequestParam(required = false) String utm_term, @RequestParam(required = false) String type){

    
//     return userRepository.getUserByUtm(utm_source, utm_medium, utm_campaign, utm_content, utm_term, type );
// }

// @GetMapping("/getUserList")
// public List<userEntity> getListType(@RequestParam String type) {

//     return userRepository.getListType(type);

// }


