/*
 * 
 */
package model.daoimplementation;
import java.io.File; 
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

// TODO: Auto-generated Javadoc
/**
 * The Class SQLMapper.
 */
public class SQLMapper {

	/**
	 * Gets the statement.
	 *
	 * @param i the i
	 * @return the statement
	 */
	public String getStatement(int i) {

		Properties props = new Properties();
		try {
			File file = new File("ressources/sql.txt");
			FileInputStream in = new FileInputStream(file);
			props.load(in);
			String res = props.getProperty(Integer.toString(i));
			in.close();
			return res;
		} catch (IOException e) {
			throw new IllegalStateException("Unable to load properties");
		}
	}

}
