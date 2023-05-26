package demo.tesSwagger.repository;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;

import demo.tesSwagger.model.userEntity;

public interface UserRepository extends JpaRepository<userEntity, Integer> {

    //store procedure @Query = berhasil
    @Query(value = "get_all_data", nativeQuery = true)
    List<userEntity> getAllData();

    /*store procedure @Procedure = berhasil
    @Procedure(name = "get_all_data", procedureName = "get_all_data")
    List<userEntity> getAllData();
    */

    //store procedure yang di defined di class entity
    //store procedure dengan anotasi @Procedure bisa dengan @transactional di controllernya 
    @Procedure(name = "userEntity.ProcQueryUtm")
    int countUtmProc(
    @Param("TransactionName") String TransactionName,
    @Param("utm_source") String utm_source, 
    @Param("utm_medium") String utm_medium,
    @Param("utm_campaign") String utm_campaign,
    @Param("utm_content") String utm_content,
    @Param("utm_term") String utm_term,
    @Param("type") String type
    );

    @Procedure(name = "userEntity.ProcQueryUtm")
    List<userEntity> getUtmList (
    @Param("TransactionName") String TransactionName,
    @Param("utm_source") String utm_source, 
    @Param("utm_medium") String utm_medium,
    @Param("utm_campaign") String utm_campaign,
    @Param("utm_content") String utm_content,
    @Param("utm_term") String utm_term,
    @Param("type") String type
    );

    @Procedure(name = "userEntity.ProcQueryUtm")
    int countTypeProc (
    @Param("TransactionName") String TransactionName,
    @Param("type") String type
    );
 
    @Procedure(name = "userEntity.ProcQueryUtm")
    List<userEntity> getTypeList (
    @Param("TransactionName") String TransactionName,
    @Param("type") String type
    );
 
}

  //Gagal
    /*store procedure dengan anotasi @Procedure untuk SP dengan query COUNT(URL) = gagal
     error : ResultSet tidak ada
             parameter out tidak ada
             @transactional = roll-back
     solusi: store procedure di defined di class entity
     @Procedure(name = "countUtmProc", procedureName = "countUtmProc")
     int countUtmProc(@Param("utm_source") String utm_source);
     */

    /*store procedure @Query dengan parameter gagal - "message": "could not extract ResultSet; SQL [n/a]; nested exception is org.hibernate.exception.SQLGrammarException: could not extract ResultSet",
    @Query(value = "ProcQueryList(:utm_source);", nativeQuery = true )
    List<userEntity> ProcQueryList(@Param("utm_source") String utm_source );
    */


    // Query dibawah tidak menggunakan store procedure

    // @Query(value = "SELECT * FROM dbo.ASK_KPR WHERE URL LIKE CONCAT('%utm_source=', :utm_source, '%') AND URL LIKE CONCAT('%utm_medium=', :utm_medium, '%') AND URL LIKE CONCAT('%utm_campaign=', :utm_campaign, '%') AND URL LIKE CONCAT('%utm_content=', :utm_content, '%') AND (:utm_term IS NULL OR URL LIKE CONCAT('%utm_term=', :utm_term, '%')) AND  (:type IS NULL OR TYPE = :type) ", nativeQuery = true)
    // List<userEntity> getUserByUtm(@Param("utm_source") String utm_source, @Param("utm_medium") String utm_medium, @Param("utm_campaign") String utm_campaign, @Param("utm_content") String utm_content, @Param("utm_term") String utm_term, @Param("type") String type);
    
    // @Query(value = "SELECT COUNT(URL) FROM dbo.ASK_KPR WHERE URL LIKE CONCAT('%utm_source=', :utm_source, '%') AND URL LIKE CONCAT('%utm_medium=', :utm_medium, '%') AND URL LIKE CONCAT('%utm_campaign=', :utm_campaign, '%') AND URL LIKE CONCAT('%utm_content=', :utm_content, '%') AND (:utm_term IS NULL OR URL LIKE CONCAT('%utm_term=', :utm_term, '%')) AND (:type IS NULL OR TYPE = :type)", nativeQuery = true)
    // int countUtm(@Param("utm_source") String utm_source , @Param("utm_medium") String utm_medium, @Param("utm_campaign") String utm_campaign, @Param("utm_content") String utm_content, @Param("utm_term") String utm_term, @Param("type") String type);

    // int countByType(String type);

    // @Query(value = "SELECT * FROM dbo.ASK_KPR WHERE (:type IS NULL OR TYPE = :type) ", nativeQuery = true)
    // List<userEntity> getListType (@Param("type") String type);