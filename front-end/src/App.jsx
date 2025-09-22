import './App.css'
import { useState } from 'react'

function App() {
  
  const [texto, setTexto] = useState('')
  const [qrCode, setQrCode] = useState('')
  const [isQrCodeVisible, setIsQrCodeVisible] = useState(false)

  async function gerarQr() {
    if (!texto.trim()) return

    try {
      setIsQrCodeVisible(false)

      const requestBody = { texto: texto }
      const response = await fetch('https://qrcode-c3sb.onrender.com', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(requestBody)
      })

      const data = await response.json()
      setQrCode(data.qrCode)
      setIsQrCodeVisible(true) 
    } catch (error) {
      console.error('Erro ao gerar QR Code:', error)
      alert('Ocorreu um erro ao gerar o QR Code.')
    }
  }

  return (
    <div className="App">
      <div className="content-wrapper">
        <div className="controls-section">
          <h1>Criador de QR Code</h1>
          <p>Cole uma URL ou digite um texto para criar seu QR Code instantaneamente.</p>
          <div className="input-section">
            <input
              type="text"
              value={texto}
              onChange={e => setTexto(e.target.value)}
              placeholder="Sua URL ou texto aqui..."
            />
            <button onClick={gerarQr} disabled={!texto.trim()}>
              Gerar
            </button>
          </div>
        </div>
        <div className="result-section">
          {qrCode ? (
            <div className={`qr-code-container ${isQrCodeVisible ? 'visible' : ''}`}>
              <img src={`data:image/png;base64,${qrCode}`} alt="QR Code" />
            </div>
          ) : (
            <div className="qr-placeholder">
              <svg xmlns="http://www.w3.org/2000/svg" width="64" height="64" viewBox="0 0 24 24" fill="none" stroke="currentColor" strokeWidth="1" strokeLinecap="round" strokeLinejoin="round"><rect x="3" y="3" width="7" height="7"></rect><rect x="14" y="3" width="7" height="7"></rect><rect x="14" y="14" width="7" height="7"></rect><rect x="3" y="14" width="7" height="7"></rect></svg>
              <span>Seu QR Code aparecerá aqui</span>
            </div>
          )}
        </div>
      </div>
    </div>
  )
}

export default App