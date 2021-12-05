package analyze

class AnalyzeController {
    AnalyzeService analyzeService
    def index() {
        analyzeService.getAnalyzeFiles()
    }
}
