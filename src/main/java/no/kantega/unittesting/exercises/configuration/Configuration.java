package no.kantega.unittesting.exercises.configuration;

/**
 * http://www.manning.com/EffectiveUnitTesting/
 */
public class Configuration {
	private boolean verboseOutput;
	private boolean showVersion;
	private String fileName;
	private boolean showWarnings;
	private boolean showDebug;

	public void processArguments(String[] args) {
        int i = 0;
        while (i < args.length) {
			if (args[i].equals("-f")) {
				i++;
                if (i < args.length) {
                    fileName = args[i];
                } else {
                    throw new InvalidArgumentException();
                }
			} else if (args[i].equals("-v")) {
				verboseOutput = true;
			} else if (args[i].equals("-w")) {
				showWarnings = true;
			} else if (args[i].equals("-d")) {
				showDebug = true;
			} else if (args[i].equals("--version")) {
				showVersion = true;
			} else {
                throw new InvalidArgumentException();
            }
            i++;
		}
	}

	public boolean isDebuggingEnabled() {
		return showDebug;
	}

	public boolean isWarningsEnabled() {
		return showWarnings;
	}

	public boolean isVerbose() {
		return verboseOutput;
	}

	public boolean shouldShowVersion() {
		return showVersion;
	}

	public String getFileName() {
		return fileName;
	}
}
