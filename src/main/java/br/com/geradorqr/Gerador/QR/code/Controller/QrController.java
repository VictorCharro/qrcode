package br.com.geradorqr.Gerador.QR.code.Controller;

import br.com.geradorqr.Gerador.QR.code.Dto.EntradaDto;
import br.com.geradorqr.Gerador.QR.code.Dto.SaidaDto;
import br.com.geradorqr.Gerador.QR.code.services.QrService;
import com.google.zxing.WriterException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/qr")
public class QrController {

    @Autowired
    private QrService qrService;

    @PostMapping("/gerar")
    public ResponseEntity<SaidaDto> gerarQr (@RequestBody EntradaDto texto){
        SaidaDto saidaDto = null;
        try {
            saidaDto = qrService.gerarQrBase64(texto);
        } catch (IOException | WriterException e) {
            throw new RuntimeException(e);
        }
        return ResponseEntity.ok().body(saidaDto);
    }
}
