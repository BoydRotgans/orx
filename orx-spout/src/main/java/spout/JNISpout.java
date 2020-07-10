package spout;

public class JNISpout {

  static {
	
	// String jvm_location = System.getProperties().getProperty("java.home") + "\\" + "bin" + "\\" + "java.exe";
	// System.out.println(jvm_location);
	String jvm_version = System.getProperty("java.version");

	// Java instead of operating system
	String sunDataModel = System.getProperty("sun.arch.data.model");
	System.out.println("Spout " + sunDataModel +"bit v2.0.6.0 - Java " + jvm_version);
	// System.out.println("Java " + sunDataModel + "bit " + jvm_version);
	if(sunDataModel.equals("32")) {
      System.out.println("32");
      //System.loadLibrary("C:\\Users\\RNDR\\git\\orx\\orx-spout\\src\\main\\resources\\Spout\\JNISpout_32.dll");
      //System.load(new File("orx-spout/src/main/resources/Spout/JNISpout_32.dll").getAbsolutePath());

    } else if(sunDataModel.equals("64")) {
      System.out.println("64");
      System.loadLibrary("JNISpout_64");
      //System.load(new File("orx-spout/src/main/resources/Spout/JNISpout_64.dll").getAbsolutePath());
      //String path = new File("orx-spout/src/main/resources/Spout/JNISpout_64.dll").getAbsolutePath();
      //System.out.println(path);
      //System.load(spout.JNISpout.class.getResource(path).getPath());
	}
  }
  
  
  // Initialization - return a pointer to a spout object
  public static native long init();
  public static native void deInit(long ptr);
  
  //=================================================================== //
  //                            SENDER                                  //
  //=================================================================== //
  
  public static native boolean createSender(String name, int width, int height, long ptr);
  
  public static native boolean updateSender(String name, int width, int height, long ptr);
  
  public static native boolean releaseSender(long ptr);
  
  public static native boolean sendTexture(int w, int h, int texID, int texTarget, boolean bInvert, long ptr);

  // SpoutControls

  public static native boolean createControl(String name, String type, float minimum, float maximum, float value, String text, long ptr);
  
  public static native boolean openControls(String name, long ptr);

  public static native int checkControls(String[] name, int[] type, float[] value, String[] text, long ptr);
  
  public static native boolean openController(String path, long ptr);

  public static native boolean closeControls(long ptr);
  
  // Shared memory

  public static native boolean createSenderMemory(String name, int width, int height, long ptr);

  public static native boolean updateSenderMemorySize(String name, int width, int height, long ptr);

  public static native boolean writeSenderString(String buf, long ptr);

  public static native void closeSenderMemory(long ptr);

  public static native long lockSenderMemory(long ptr);

  public static native void unlockSenderMemory(long ptr);
    

  //=================================================================== //
  //                           RECEIVER                                 //
  //=================================================================== //

  public static native boolean createReceiver(String name, int[] dim, long ptr);
  
  public static native boolean releaseReceiver(long ptr);

  public static native boolean receivePixels(int[] dim, int[] pix, long ptr);

  public static native boolean receiveTexture(int[] dim, int texID, int texTarget, boolean bInvert, long ptr);

  public static native boolean drawTexture(boolean bInvert, long ptr);

  public static native boolean senderDialog(long ptr);

  public static native String getSpoutSenderName(long ptr);
  
  
  
  //=================================================================== //
  //                            COMMON                                  //
  //=================================================================== //

  public static native int getTextureID(long ptr);

  public static native boolean getMemoryShareMode(long ptr);
  
  public static native int getShareMode(long ptr);
 
  
}
