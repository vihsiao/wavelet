import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;

class Handler implements URLHandler {
    String s = "";

    ArrayList<String> queries = new ArrayList<String>();

    public String handleRequest(URI url) {
        if (url.getPath().equals("/")) {
            return String.format("Current list: %s", queries.toString());
        }

        else if (url.getPath().contains("/add")) {
            String[] parameters = url.getQuery().split("=");
            queries.add(parameters[1]);
            return String.format("%s added to the list!", parameters[1]);
        }

        else {
            if (url.getPath().contains("/search")) {
                String[] parameters = url.getQuery().split("=");
                String return_str = "";

                for (int i = 0; i < queries.size(); i++) {
                    if (queries.get(i).contains(parameters[1])) {
                        return_str += (queries.get(i) + " ");
                    }
                }

                return return_str;
            }

            if (url.getPath().contains("/delete")) {
                
            }
        }

        return "404 Not Found!";
    }
}

public class SearchEngine {
    public static void main(String[] args) throws IOException {
        if(args.length == 0){
            System.out.println("Missing port number! Try any number between 1024 to 49151");
            return;
        }

        int port = Integer.parseInt(args[0]);

        Server.start(port, new Handler());
    }
}
