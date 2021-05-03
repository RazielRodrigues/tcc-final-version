import java.sql.*;
import java.text.SimpleDateFormat;
public class BenchmarkMySQL_TCC {
   static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
   static final String DB_URL = "jdbc:mysql://127.0.0.1:3306/benchmark_mysql?autoReconnect=true&useSSL=false";
   static final String USER = "finley";
   static final String PASS = "password";
   public static void main(String[] args) {
   Connection conn = null;
   CallableStatement stmt = null;
   try{
      Class.forName("com.mysql.jdbc.Driver");
      System.out.println("Conectanco ao banco de dados: " + DB_URL);
      conn = DriverManager.getConnection(DB_URL,USER,PASS);
      System.out.println("Conectado!");
      stmt = conn.prepareCall("{CALL insert_app()}");
      long started = 0;
      long end = 0;
      long difference = 0;
      long total = 0;
      long executions = 5000000;
      SimpleDateFormat simpleDateFormat = new SimpleDateFormat("S.ss");
      SimpleDateFormat totalFormat = new SimpleDateFormat("mm:ss:S");
      System.out.println("Iniciando Benchmark para " + executions + " de chamadas a procedure");
      for(long i = 0; i <= executions; i++){
         started = System.currentTimeMillis();
         stmt.execute();
         end = System.currentTimeMillis();
         difference = end - started;
         total += difference;
         if(i == 10000){
            String time = simpleDateFormat.format(total);
            System.out.println(i + " chamadas a procedure Txn/Sec do intervalo entre a execução de cada uma = " + time);
         }else if(i == 2000000){
            String time = simpleDateFormat.format(total);
            System.out.println(i + " chamadas a procedure Txn/Sec do intervalo entre a execução de cada uma = " + time);
         }else if(i == 3000000){
            String time = simpleDateFormat.format(total);
            System.out.println(i + " chamadas a procedure Txn/Sec do intervalo entre a execução de cada uma = " + time);
         }else if(i == 4000000){
            String time = simpleDateFormat.format(total);
            System.out.println(i + " chamadas a procedure Txn/Sec do intervalo entre a execução de cada uma = " + time);
         }else if(i == 5000000){
            String time = simpleDateFormat.format(total);
            System.out.println(i + " chamadas a procedure Txn/Sec do intervalo entre a execução de cada uma = " + time);
         }
      }
      String finalTime = totalFormat.format(total);
      String averageTime = simpleDateFormat.format(total / 5);
      System.out.println("Media de intervalo de Txn/Sec a cada execução da procedure: " + (averageTime) );
      System.out.println("Tempo total da execução do benchmark = " + (finalTime) );
      stmt.close();
      conn.close();
   }catch(SQLException se){
      se.printStackTrace();
   }catch(Exception e){
      e.printStackTrace();
   }
 }
}