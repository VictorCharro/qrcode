package br.com.geradorqr.Gerador.QR.code.services;

import br.com.geradorqr.Gerador.QR.code.Dto.EntradaDto;
import br.com.geradorqr.Gerador.QR.code.Dto.SaidaDto;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Base64;

@Service
public class QrService {

    public byte[] gerarqr(EntradaDto texto) throws IOException, WriterException {
        QRCodeWriter writer = new QRCodeWriter();
        BitMatrix matrix = writer.encode(texto.getTexto(), BarcodeFormat.QR_CODE, 400, 400);
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        MatrixToImageWriter.writeToStream(matrix, "PNG", os);
        return os.toByteArray();
    }

    public SaidaDto gerarQrBase64(EntradaDto texto) throws IOException, WriterException {
        byte[] png = gerarqr(texto);
        String base = Base64.getEncoder().encodeToString(png);
        SaidaDto saida = new SaidaDto();
        saida.setQrCode(base);
        return saida;
    }
}