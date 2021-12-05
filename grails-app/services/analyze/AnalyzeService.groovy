package analyze

import grails.gorm.transactions.Transactional

import java.text.ParseException
import java.text.SimpleDateFormat


@Transactional
class AnalyzeService {

    //log 內容
    private MessageI messageI
    private List<MessageI> messageL = new LinkedList<>()

    def getAnalyzeFiles() {

//        readFile()

        String fileContentL = """八月 03, 2021 2:44:31 下午 org.apache.catalina.loader.WebappClassLoaderBase checkThreadLocalMapForLeaks"""

        fileContentL.eachLine {fileContentI ->
            analyzeContent(fileContentI)
        }

        messageL.each {
            print "logTime = "+it.logTime
            print ",logTitle = "+ it.logTitle
            print ",logContent = "+ it.logContent
            println ""
        }


    }


    private void analyzeContent(String fileContentI){

        println "fileContentI= "+fileContentI

        //分析
        String logTime
        boolean isLogTitle = false
        Date logTimeT

        if(fileContentI.length() > 23){
            logTime = fileContentI.substring(0,23)
            logTime = logTime.replaceAll(",",".")


            try{
                logTimeT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").parse(logTime)

                isLogTitle = true
            }catch(ParseException ex){
                isLogTitle = false
            }
        }

        if(isLogTitle){
            if(logTimeT){
                if(messageI){
                    messageL.add(messageI)
                    messageI = new MessageI()
                }else{
                    messageI = new MessageI()
                }
                messageI.logTime = logTimeT
            }
            messageI.logTitle = fileContentI.substring(23)
        }else{
            messageI.logContent.append(fileContentI)
        }
    }

    /**
     * 讀取檔案內容
     */
    private void readFile(){
        String USER_HOME = System.getProperty("user.home")
        String filePath = USER_HOME+'/logAnalyze/logFiles/'
        String fileName = filePath+"/catalina.out"
        BufferedReader objReader = null
        String strCurrentLine
        objReader = new BufferedReader(new FileReader(fileName))

        while ((strCurrentLine = objReader.readLine()) != null) {
            analyzeContent(strCurrentLine)
        }
    }
}
