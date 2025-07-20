package util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class CompactadorZip {
    public static void compactarArquivo(String caminhoArquivo, String caminhoZip) throws Exception {
        try (
            FileOutputStream fos = new FileOutputStream(caminhoZip);
            ZipOutputStream zipOut = new ZipOutputStream(fos);
            FileInputStream fis = new FileInputStream(new File(caminhoArquivo))
        ) {
            File fileToZip = new File(caminhoArquivo);
            ZipEntry zipEntry = new ZipEntry(fileToZip.getName());
            zipOut.putNextEntry(zipEntry);
            
            byte[] bytes = new byte[1024];
            int length;
            while ((length = fis.read(bytes)) >= 0) {
                zipOut.write(bytes, 0, length);
            }
        }
    }
}
