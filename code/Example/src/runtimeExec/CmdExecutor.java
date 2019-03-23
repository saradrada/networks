package runtimeExec;

import java.io.IOException;

public class CmdExecutor {

	public static void main(String[] args) throws IOException, InterruptedException {

		try {
			// Run "dir" and "ping" command on cmd
			Runtime.getRuntime().exec("cmd /c start cmd.exe /K \" dir && ping localhost \"");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
