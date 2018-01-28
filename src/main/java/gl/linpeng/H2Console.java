package gl.linpeng;

import java.sql.SQLException;

import org.h2.tools.Console;
import org.h2.tools.Server;	

/**
 * H2 console
 * 
 * @author linpeng
 *
 */
public class H2Console {

	public static void start() throws SQLException {
		Server.createTcpServer("-tcpPort", "9092", "-tcpAllowOthers").start();
		String[] args = { "-web", "-browser" };
		Console.main(args);
	}

	public static void stop() throws SQLException {
		Server.shutdownTcpServer("tcp://127.0.0.1:9092", "", true, true);
	}

	public static void main(String[] args) throws Exception {
		H2Console.start();
	}
}
