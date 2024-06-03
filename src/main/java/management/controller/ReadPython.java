/*package management.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.springframework.stereotype.Component;
@Component
public class ReadPython {
	public String ReadPython(Double ProductBrand, Double Material, Double ProductionWay) throws IOException, InterruptedException {
		String data = "D:\\WebThoiTrang\\WebThoiTranng\\src\\main\\java\\python\\AI.sav";

		ProcessBuilder pb = new ProcessBuilder("python",
				"D:\\WebThoiTrang\\WebThoiTranng\\src\\main\\java\\python\\load_AI.py",
				data, ProductBrand.toString(), Material.toString(), ProductionWay.toString()).inheritIO();

        Process p = pb.start();
        p.waitFor();

        BufferedReader bfr = new BufferedReader(new InputStreamReader(p.getInputStream()));
        String line;
        StringBuilder label = new StringBuilder();

        while ((line = bfr.readLine()) != null) {
            label.append(line).append("\n");
        }

        // In kết quả ra màn hình hoặc thực hiện các xử lý khác với kết quả
        String result = label.toString();
        System.out.println("Kết quả từ tiến trình Python:");
        System.out.println(result);

        return result;
    }
}

*/
package management.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.springframework.stereotype.Component;

@Component
public class ReadPython {

	public String layNhanSP(Double ProductBrand, Double Material, Double ProductionWay)
			throws IOException, InterruptedException {
		String load_AI = "D:\\WebThoiTrang\\WebThoiTranng\\src\\main\\java\\python\\load_AI.py";
		String AI = "D:\\WebThoiTrang\\WebThoiTranng\\src\\main\\java\\python\\AI.sav";

		ProcessBuilder pb = new ProcessBuilder("python", load_AI, AI, ProductBrand.toString(),
				Material.toString(), ProductionWay.toString());

		// Redirect both standard output and error to Java's standard output
		pb.redirectErrorStream(true);

		Process process = pb.start();
		process.waitFor();

		// Read the output from Python
		BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
		String result = "";
		String line;
		System.out.println("Kết quả từ tiến trình Python:");
		while ((line = reader.readLine()) != null) {
			System.out.println(line);
			result = line;
		}

		System.out.println(result);

		return result;
	}

}
