package ex

import analyze.AnalyzeService
import grails.util.Environment

class ExampleJob {

    AnalyzeService analyzeService

    static triggers = {
        if (Environment.current == Environment.DEVELOPMENT) {
//            cron name: 'ExampleJob', startDelay: 10000, cronExpression: '0 0 1 * * ?'
            //simple name: 'ExampleJob', repeatInterval: 1000000l // execute job
        }
    }

    def execute() {
        analyzeService.getAnalyzeFiles()
    }
}
