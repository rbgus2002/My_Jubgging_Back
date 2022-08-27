package my.plogging.Service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import my.plogging.Repository.PublicTrashAddressRepository;
import my.plogging.domain.PublicTrashAddress;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Service;

import javax.print.attribute.DocAttributeSet;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.*;

@Service
@RequiredArgsConstructor
public class ExcelPOIHelper {
    private final PublicTrashAddressRepository publicTrashAddressRepository;

    public void readExcelAndSave() throws IOException {
        String fileLocation = "/Users/choegyuhyeon/Downloads/서울특별시 가로쓰레기통 현황_202106.xlsx";
        FileInputStream file = new FileInputStream(fileLocation);
        Workbook workbook = new XSSFWorkbook(file);
        Sheet sheet = workbook.getSheetAt(0);


        // Sheet로 읽어들인 데이터를 다시 Row와 Cell로 나눈다.
        List<PublicTrashAddress> list = new ArrayList<>();
        for (int j = 2; j < 5376; j++) {
            Row row = sheet.getRow(j);

            // set address
            String address = "서울 ";
            address += row.getCell(2).toString() + " ";
            address += row.getCell(3).toString() + " ";
            address += row.getCell(4).toString();

            //set kind
            String kind = divideKind(row.getCell(6).toString());

            //set coordinate
            Map map = getGeoDataByAddress(address);
            String latitude = "";
            String longitude = "";
            for (Object tmp : map.keySet()) {
                if (tmp.toString().equals("latitude"))
                    latitude = map.get(tmp.toString()).toString();
                else if (tmp.toString().equals("longitude"))
                    longitude = map.get(tmp.toString()).toString();
            }

            //set spec
            String spec = row.getCell(5).toString().strip();

            PublicTrashAddress publicTrashAddress = PublicTrashAddress.builder()
                    .address(address)
                    .kind(kind)
                    .latitude(latitude)
                    .longitude(longitude)
                    .spec(spec)
                    .build();

            // save
            publicTrashAddressRepository.save(publicTrashAddress);
        }
    }

    private String divideKind(String kind) {
        String answer = "";
        if (kind.contains("일반"))
            answer = "General";
        else if (kind.contains("재활용"))
            answer = "Recycle";
        else if (kind.contains("담배"))
            answer = "Smoking";

        // error detection
        if (answer.equals(""))
            System.out.println(kind + "is Wrong!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");

        return answer;
    }

    private static Map<String, String> getGeoDataByAddress(String completeAddress) {
        Map map = new HashMap();
        try {
            String API_KEY = "AIzaSyDARndJ-O_I1yeKhlxcjA3KIFL_8o0OhD0";
            String surl = "https://maps.googleapis.com/maps/api/geocode/json?address=" + URLEncoder.encode(completeAddress, "UTF-8") + "&key=" + API_KEY;
            URL url = new URL(surl);
            InputStream is = url.openConnection().getInputStream();
            System.out.println(url);

            BufferedReader streamReader = new BufferedReader(new InputStreamReader(is, "UTF-8"));

            StringBuilder responseStrBuilder = new StringBuilder();
            String inputStr;
            while ((inputStr = streamReader.readLine()) != null) {
                responseStrBuilder.append(inputStr);
            }

            JSONObject objData = (JSONObject) new JSONParser().parse(responseStrBuilder.toString());
            JSONArray jsonArray = (JSONArray) objData.get("results");
            JsonNode parent = new ObjectMapper().readTree(jsonArray.toJSONString());
            if(parent.findValue("geometry") != null){
                map.put("longitude", parent.findValue("geometry").findValue("location").get("lng"));
                map.put("latitude", parent.findValue("geometry").findValue("location").get("lat"));
            }else{
                System.out.println("sdasdasdasnull sibal");
            }

        } catch (MalformedURLException ex) {
            throw new RuntimeException(ex);
        } catch (UnsupportedEncodingException ex) {
            throw new RuntimeException(ex);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }


}
// 서울 종로구 자하문로 자하문로 44


/*
@Builder
public PublicTrashAddress(String address, String kind, String coordinate, String spec) {
    this.address = address;
    this.kind = kind;
    this.coordinate = coordinate;
    this.spec = spec;
}

Example
{
    "address":"서울 종로구 사직로 경복궁역 4번출구"
    "kind":"General",
    "coordinate":"021030120",
    "spec":"지하철역 입구"
}

 */



/*
                switch (cell.getCellType()) {
                    case STRING:
                        if (cell.toString().contains("일반"))
                            data.get(i).add("General");
                        else if(cell.toString().contains("재활용"))
                            data.get(i).add("Recyclable");
                        else if(cell.toString().contains("담배"))
                            data.get(i).add("Smoking");
                        else
                            data.get(i).add(cell.getRichStringCellValue().getString());
                    default:
                        data.get(i).add(" ");

                }
 */