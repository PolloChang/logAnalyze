package util

import java.text.ParseException
import java.text.SimpleDateFormat

/**
 * 特殊字串轉日期
 */
class ConvertStringToDate {

    /**
     * 中文格式轉日期格式
     * @param inputS
     * @return
     * @throws ParseException
     * @throws Exception
     */
    Date convertCHT(String inputS)
            throws ParseException,Exception{
        Date returnVal
        String[] inputA = inputS.split(" ")

        if(inputA.length != 5){
            throw new Exception("${inputS} 非正常格式")
        }

        inputA.each { println it}

        String dateYYYY = inputA[2]
        String dateMM = inputA[0]
        String dateDD = inputA[1].replaceAll(",","")
        String dateTime = inputA[3]
        String dateAMPM = inputA[4]

        String[] dateTimeA = dateTime.split(":")
        int timeHH = dateTimeA[0] as int
        int timeMm = dateTimeA[1] as int
        int timeSs = dateTimeA[2] as int

        if(dateAMPM == "下午"){
            timeHH += 12
        }

        String logTime = "${dateYYYY}-${covertCHMToMM(dateMM)}-${dateDD} ${String.format("%02d", timeHH)}:${timeMm}:${timeSs}.000"

        println logTime

        try{
            returnVal = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").parse(logTime)
        }catch(ParseException ex){
            throw ex
        }

        return returnVal
    }


    /**
     * 中文月份轉西元月
     * @param inputS 中文月份
     * @return
     * @throws Exception
     */
    private String covertCHMToMM(String inputS) throws Exception{

        String returnVal

        inputS = inputS.replaceAll("月","")

        String[] CHM = ["一","二","三","四","五","六","七","八","九","十","十一","十二"]

        int countI = CHM.findIndexOf {it == inputS} +1

        if(!countI){
            throw new Exception("${inputS} 非月份")
        }

        returnVal = String.format("%02d", countI)

        return returnVal
    }
}

