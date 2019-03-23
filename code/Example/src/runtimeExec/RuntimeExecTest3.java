package runtimeExec;

import java.io.IOException;

public class RuntimeExecTest3 {

    public static void main(String args[])
            throws IOException
        {
            Runtime.getRuntime().exec("cmd /c start Wireshark.exe");
        }
}

