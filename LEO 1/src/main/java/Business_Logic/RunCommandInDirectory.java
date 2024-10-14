package Business_Logic;


import com.tyss.optimize.common.util.*;
import com.tyss.optimize.nlp.util.*;
import com.tyss.optimize.nlp.util.annotation.*;
import java.util.*;
import java.io.*;





public class RunCommandInDirectory implements Nlp {
    @InputParams({@InputParam(name = "Path", type = "java.lang.String"), @InputParam(name = "Command", type = "java.lang.String")})

      public NlpResponseModel execute(NlpRequestModel nlpRequestModel) throws NlpException {
        
          NlpResponseModel nlpResponseModel = new NlpResponseModel();
          Map<String, Object> programElementsInput = nlpRequestModel.getAttributes();
          String Path = (String) programElementsInput.get("Path");
          String Command = (String) programElementsInput.get("Command");
         // int returnValue=0;
          try {
        	  ProcessBuilder builder = new ProcessBuilder("cmd.exe", "/c", Command);
              builder.directory(new File(Path));
              Process process = builder.start();
              BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
              BufferedReader errorReader = new BufferedReader(new InputStreamReader(process.getErrorStream()));

              StringBuilder outputBuilder = new StringBuilder();
              StringBuilder errorBuilder = new StringBuilder();

              String line;
              while ((line = reader.readLine()) != null) {
                  outputBuilder.append(line).append("\n");
                  if (line.contains("Successfully loaded snapshot")||(line.contains("Boot completed ")&&line.contains("Increasing screen off timeout"))) {
					break;
				}
              }

//              while ((line = errorReader.readLine()) != null) {
//                  errorBuilder.append(line).append("\n");
//              }

//              int exitCode = process.waitFor();
              nlpResponseModel.setMessage("Successfully launched the emulator");
              nlpResponseModel.setStatus(CommonConstants.pass);
              }
          catch(Exception e) {
              nlpResponseModel.setMessage("Failed to launched the emulator");
              nlpResponseModel.setStatus(CommonConstants.fail);
              }

          // Your program element business logic ends here ...
         // nlpResponseModel.getAttributes().put("Sum of Two numbers", returnValue);
          return nlpResponseModel;
      }

      @Override
      public List<String> getTestParameters() throws NlpException {
        List<String> params = new ArrayList<>();
        return params;
      }

      @Override
      public StringBuilder getTestCode() throws NlpException {
         StringBuilder sb = new StringBuilder();
        return sb;
      }
  }