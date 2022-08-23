package my.plogging.Service;

import lombok.RequiredArgsConstructor;
import my.plogging.Repository.PublicTrashAddressRepository;
import my.plogging.domain.PublicTrashAddress;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.IOException;
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
        // 개별 Cell에 접근할 때 CellType을 확인하는 이유는 적절한 객체 타입으로 저장하기 위함
        List<PublicTrashAddress> list = new ArrayList<>();
        int i = 0;
        for (Row row : sheet) {
            //첫 두 개 행 제외
            if (i++ < 2)
                continue;

            // set address
            String address = "서울 ";
            address += row.getCell(2).toString() + " ";
            address += row.getCell(3).toString() + " ";
            address += row.getCell(4).toString();

            //set kind
            String kind = divideKind(row.getCell(6).toString());

            //set coordinate
            String coordinate = "123012030213"; //tmp

            //set spec
            String spec = row.getCell(5).toString().strip();

            PublicTrashAddress publicTrashAddress = PublicTrashAddress.builder()
                    .address(address)
                    .kind(kind)
                    .coordinate(coordinate)
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
        if(answer.equals(""))
            System.out.println(kind + "is Wrong!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");

        return answer;
    }
}

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