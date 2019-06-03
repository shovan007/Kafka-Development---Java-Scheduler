package simplescheduler;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Properties;

import org.json.JSONObject;
import org.json.simple.JSONArray;

public class test2 {

	public static void main(String[] args) {
		
		testing t2=new testing();
		HashSet<String>i2=((testing)t2).met2();
		for(String v:i2) {
			//System.out.println(v);
			try {
				Class.forName("com.mysql.jdbc.Driver");
				Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/kafka_test", "root", "");
		
				// Kafka_Properties:
				Properties props = new Properties();
				props.put("bootstrap.servers", "localhost:9092,localhost:9093,localhost:9094");
				props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
				props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
				
				Statement st1 = con.createStatement();
				ResultSet rs1 = st1.executeQuery("SELECT * FROM account where account = " + v);
				JSONObject json_account = new JSONObject();
				ResultSetMetaData rsmd = rs1.getMetaData();
				JSONArray array_contact = new JSONArray();
				JSONArray array_address = new JSONArray();

				while (rs1.next()) {
					json_account.put("account", rs1.getInt("account"));
					json_account.put("name", rs1.getString("name"));
					json_account.put("updated", rs1.getDate("updated"));

					Statement st2 = con.createStatement();
					ResultSet rs2 = st2.executeQuery("SELECT * FROM contact where account = " + rs1.getInt("account"));
					Statement st3 = con.createStatement();

					ResultSetMetaData rsmd1 = rs2.getMetaData();
					array_contact = new JSONArray();
					while (rs2.next()) {

						int numColumns = rsmd1.getColumnCount();
						JSONObject json_contact = new JSONObject();
						for (int i = 1; i <= numColumns; i++) {
							String column_name = rsmd1.getColumnName(i);
							json_contact.put(column_name, rs2.getObject(column_name));

						}
						array_contact.add(json_contact);

						ResultSet rs3 = st3
								.executeQuery("SELECT * FROM address where contact_id = " + rs2.getInt("contact_id"));
						ResultSetMetaData rsmd2 = rs3.getMetaData();
						array_address = new JSONArray();
						while (rs3.next()) {
							int numColumns1 = rsmd2.getColumnCount();
							JSONObject json_address = new JSONObject();
							for (int a = 1; a <= numColumns1; a++) {
								String colum_name = rsmd2.getColumnName(a);
								json_address.put(colum_name, rs3.getObject(colum_name));
							}
							array_address.add(json_address);
						}
						json_contact.put("address", array_address);
					}

					json_account.put("contact", array_contact);
					rs2.close();

				} // while rs1
				System.out.println(json_account.toString());

				String val = json_account.toString();

				String key = "Key1";
				String value = val;
				String topicName = "avro";
				

//				try {
//					while (true) {
	//
//						Producer<String, String> producer = new KafkaProducer<String, String>(props);
//						ProducerRecord<String, String> record = new ProducerRecord<String, String>(topicName, key, value);
//						producer.send(record);
//						producer.close();
//						System.out.println("###########Multi-Query-producer Completed################");
	//
//						Thread.sleep(60 * 1000);
//					}
//				} catch (InterruptedException e) {
//					e.printStackTrace();
//				}
				rs1.close();
				con.close();

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
			
		}
		
	}

