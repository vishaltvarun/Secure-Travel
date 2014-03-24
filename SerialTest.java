import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import gnu.io.CommPortIdentifier; 
import gnu.io.SerialPort;
import gnu.io.SerialPortEvent; 
import gnu.io.SerialPortEventListener; 
import java.util.Enumeration;
import java.util.Properties;
import java.sql.*;

public class SerialTest implements SerialPortEventListener {
SerialPort serialPort;

    /** The port we're normally going to use. */
private static final String PORT_NAMES[] = {                  "/dev/tty.usbserial-A9007UX1", // Mac OS X
        "/dev/ttyUSB0", // Linux
        "COM10", // Windows
};
private BufferedReader input;
private OutputStream output;
String inputLine=null;
private static final int TIME_OUT = 10000;
private static final int DATA_RATE = 9600;

public void initialize() {
    CommPortIdentifier portId = null;
    Enumeration portEnum = CommPortIdentifier.getPortIdentifiers();

    //First, Find an instance of serial port as set in PORT_NAMES.
    while (portEnum.hasMoreElements()) {
        CommPortIdentifier currPortId = (CommPortIdentifier) portEnum.nextElement();
        for (String portName : PORT_NAMES) {
            if (currPortId.getName().equals(portName)) {
                portId = currPortId;
                break;
            }
        }
    }
    if (portId == null) {
        System.out.println("Could not find COM port.");
        return;
    }

    try {
        serialPort = (SerialPort) portId.open(this.getClass().getName(),
                TIME_OUT);
        serialPort.setSerialPortParams(DATA_RATE,
                SerialPort.DATABITS_8,
                SerialPort.STOPBITS_1,
                SerialPort.PARITY_NONE);

        // open the streams
        input = new BufferedReader(new InputStreamReader(serialPort.getInputStream()));
        output = serialPort.getOutputStream();

        serialPort.addEventListener(this);
        serialPort.notifyOnDataAvailable(true);
    } catch (Exception e) {
        System.err.println(e.toString());
    }
}


public synchronized void close() {
    if (serialPort != null) {
        serialPort.removeEventListener();
        serialPort.close();
    }
}

/*public synchronized void serialEvent(SerialPortEvent oEvent) {
    if (oEvent.getEventType() == SerialPortEvent.DATA_AVAILABLE) {
        try {
          
            if (input.ready()) {
                inputLine = input.readLine();
                            System.out.println(inputLine);
            }

        } catch (Exception e) {
           e.printStackTrace();
        }
    }
    // Ignore all the other eventTypes, but you should consider the other ones.
}*/


public synchronized void serialEvent(SerialPortEvent oEvent) {
	

    try {
        switch (oEvent.getEventType() ) {
            case SerialPortEvent.DATA_AVAILABLE: 
                if ( input == null ) {
                	System.out.println("here11");
                    input = new BufferedReader(
                        new InputStreamReader(
                                serialPort.getInputStream()));
                }
               String inputLine = input.readLine();
                //System.out.println(input.readLine().trim());
           if(inputLine.equals(""))
            {
            }
            else
            {
            	String url = "jdbc:mysql://localhost/secureplanet";
                Properties prop=new Properties();
                prop.setProperty("user","root");
                prop.setProperty("password","toor");
                Driver d=new com.mysql.jdbc.Driver();
                Connection conn = d.connect(url,prop);
                if(conn==null)   {
                    System.out.println("connection failed");
                    return;
                }
                DatabaseMetaData dm =conn.getMetaData();
                String dbversion=dm.getDatabaseProductVersion();
                String dbname=dm.getDatabaseProductName();
                System.out.println("name:"+dbname);
                System.out.println("version:"+dbversion);

          String rfidtoken=  inputLine.trim();
            	Statement stmt = conn.createStatement();
                
                 Double lat=17.4416;
                 Double lng=78.3826;
                 
                 String sql = "INSERT INTO smarttracking " +
                         "VALUES ('"+rfidtoken+"','"+lat+"','"+lng+"')";
            stmt.executeUpdate(sql);
                 
                
            }
                break;
 
            default:
                break;
        }
    } 
    catch (Exception e) {
       e.printStackTrace();
    }
}



public static void main(String[] args) throws Exception {


    SerialTest main = new SerialTest();
    main.initialize();
    Thread t=new Thread() {
        public void run() {
            //the following line will keep this app alive for 1000    seconds,
            //waiting for events to occur and responding to them    (printing incoming messages to console).
            try {Thread.sleep(1000000);} catch (InterruptedException    ie) {}
        }
    };
    t.start();
    System.out.println("Started");
}
}