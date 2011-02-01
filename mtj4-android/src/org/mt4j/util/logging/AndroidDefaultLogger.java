package org.mt4j.util.logging;


import android.util.Log;

public class AndroidDefaultLogger implements ILogger {
	private String name;
	
	public AndroidDefaultLogger(){	}
	
	private AndroidDefaultLogger(String name){
		this.name = name;
	}
	
	public void setLevel(int level) {
//		switch (level) {
//		case OFF:
//			this.logger.setLevel(Level.OFF); 
//			break;
//		case ALL:
//			this.logger.setLevel(Level.ALL); 
//			break;
//		case INFO:
//			this.logger.setLevel(Level.INFO); 
//			break;
//		case DEBUG:
//			this.logger.setLevel(Level.CONFIG);  //TODO which level matches debug?
//			break;
//		case WARN:
//			this.logger.setLevel(Level.WARNING); 
//			break;
//		case ERROR:
//			this.logger.setLevel(Level.SEVERE); 
//			break;
//		default:
//			break;
//		}
	}

	public void info(Object message) {
		Log.i(name, message.toString());
	}

	public void debug(Object message) {
		Log.d(name, message.toString());
	}

	public void warn(Object message) {
		Log.w(name, message.toString());
	}

	public void error(Object message) {
		Log.e(name, message.toString());
	}

	public ILogger createNew(String name) {
		return new AndroidDefaultLogger(name);
	}

	public int getLevel() {
//		Level level = this.logger.getLevel();
//		if (level.equals(Level.OFF)){
//			return ILogger.OFF;
//		}else if (level.equals(Level.ALL)){
			return ILogger.ALL;
//		}else if (level.equals(Level.INFO)){
//			return ILogger.INFO;
//		}else if (level.equals(Level.CONFIG)){
//			return ILogger.DEBUG;
//		}else if (level.equals(Level.WARNING)){
//			return ILogger.WARN;
//		}else if (level.equals(Level.SEVERE)){
//			return ILogger.ERROR;
//		}else{
//			return -1;
//		}
	}

}