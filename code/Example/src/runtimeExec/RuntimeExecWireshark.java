package runtimeExec;

import java.io.IOException;

public class RuntimeExecWireshark {

    public static void main(String args[])
            throws IOException
        {
            Runtime.getRuntime().exec("cmd /c start Wireshark.exe");
        }
}

