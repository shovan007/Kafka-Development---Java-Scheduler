/**
 * 
 */
package simplescheduler;

import java.sql.*;
import org.json.JSONObject;
import org.json.simple.JSONArray;
import org.apache.kafka.clients.producer.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;
import java.util.Timer;

/**
 * @author Vikram
 *
 */

public class testing {

	public HashSet<String> met2() {

		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/kafka_test", "root", "");

			Statement st1 = con.createStatement();
			ResultSet rs1 = st1
					.executeQuery("SELECT account FROM account WHERE updated BETWEEN '2019-05-03' and current_date");
			ResultSetMetaData rsmd = rs1.getMetaData();
			int columnCount = rsmd.getColumnCount();
			ArrayList<String> accountResultList = new ArrayList<String>(columnCount);

			while (rs1.next()) {
				int i = 1;
				while (i <= columnCount) {
					accountResultList.add(rs1.getString(i++));
				}
			}

			System.out.println("account : " + accountResultList);// 4 times

			Statement st2 = con.createStatement();
			// st2.setFetchSize(1000);
			ResultSet rs2 = st2
					.executeQuery("SELECT account FROM contact WHERE updated BETWEEN '2019-05-03' and current_date");
			ResultSetMetaData rsmd1 = rs2.getMetaData();
			int numColumns = rsmd1.getColumnCount();

			ArrayList<String> contactResultList = new ArrayList<String>(numColumns);

			while (rs2.next()) {
				int j = 1;
				while (j <= numColumns) {
					contactResultList.add(rs2.getString(j++));

				}

			}
			System.out.println("contact : " + contactResultList);// 4 times

			Statement st3 = con.createStatement();
			ResultSet rs3 = st3
					.executeQuery("SELECT account FROM address WHERE updated BETWEEN '2019-04-30' and current_date");
			ResultSetMetaData rsmd2 = rs3.getMetaData();
			ArrayList<String> addressResultList = new ArrayList<String>(numColumns);

			int numColumns1 = rsmd2.getColumnCount();
			while (rs3.next()) {
				int k = 1;
				while (k <= numColumns) {
					addressResultList.add(rs3.getString(k++));
				}

			}
			System.out.println("address : " + addressResultList);
			ArrayList<String> FinalResultList = new ArrayList<String>();
			FinalResultList.addAll(accountResultList);
			FinalResultList.addAll(contactResultList);
			FinalResultList.addAll(addressResultList);
			System.out.println("FinalList : " + FinalResultList);
	        HashSet<String> set = new HashSet<String>(FinalResultList);
			System.out.println("FinalSet : " + set);
			int LengthSet;
			LengthSet = set.size();
			System.out.println(LengthSet);
			
			
			
		
			
			
			// I need LenSet and Set in the other program;

			rs2.close();
			

			rs1.close();
			con.close();
			return set;

		} catch (

		Exception e) {
			e.printStackTrace();
		}
		//return set;
		return null;
		
	}
}
