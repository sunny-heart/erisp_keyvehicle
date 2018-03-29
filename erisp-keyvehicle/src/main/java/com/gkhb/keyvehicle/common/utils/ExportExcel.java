package com.gkhb.keyvehicle.common.utils;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.VerticalAlignment;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

/***
 * @author lsf
 */
public class ExportExcel {
	
	public final static String exportExcel(HttpServletResponse response, String fileName,
			String[] warningTitle, List<Object> warningList,
			String[] accidentTitle, List<Object> accidentList,
			String[] illegalTitle, List<Object> illegalList) {
		String result="系统提示：Excel文件导出成功！";  
		// 以下开始输出到EXCEL
		try {    
			//定义输出流，以便打开保存对话框______________________begin
			//OutputStream os = response.getOutputStream();// 取得输出流    
			ServletOutputStream os = response.getOutputStream();
			response.reset();// 清空输出流      
			response.setHeader("Content-disposition", "attachment; filename="+ new String(fileName.getBytes("GB2312"),"ISO8859-1"));
			// 设定输出文件头      
			response.setContentType("application/msexcel");// 定义输出类型    
			//定义输出流，以便打开保存对话框_______________________end

			/** **********创建工作簿************ */
			WritableWorkbook workbook = Workbook.createWorkbook(os);
			
			/** ************设置单元格字体************** */
			WritableFont NormalFont = new WritableFont(WritableFont.ARIAL, 10);
			WritableFont BoldFont = new WritableFont(WritableFont.ARIAL, 10,WritableFont.BOLD);
			
			/** ************以下设置三种单元格样式，灵活备用************ */
			// 用于标题居中
			WritableCellFormat wcf_center = new WritableCellFormat(BoldFont);
			wcf_center.setBorder(Border.NONE, BorderLineStyle.THIN); // 线条
			wcf_center.setVerticalAlignment(VerticalAlignment.CENTRE); // 文字垂直对齐
			wcf_center.setAlignment(Alignment.LEFT); // 文字水平对齐
			wcf_center.setWrap(false); // 文字是否换行

			// 用于正文居左
			WritableCellFormat wcf_left = new WritableCellFormat(NormalFont);
			wcf_left.setBorder(Border.NONE, BorderLineStyle.THIN); // 线条
			wcf_left.setVerticalAlignment(VerticalAlignment.CENTRE); // 文字垂直对齐
			wcf_left.setAlignment(Alignment.LEFT); // 文字水平对齐
			wcf_left.setWrap(true); // 文字是否换行   
			
			/**************************************************************预警工作表 start********************************************/
			/** **********创建工作表************ */
			WritableSheet warningSheet = workbook.createSheet("预警信息", 0);
			/** **********设置纵横打印（默认为纵打）、打印纸***************** */
			jxl.SheetSettings warningSheetset = warningSheet.getSettings();
			warningSheetset.setProtected(false);

			/** ***************第一行列标题********************* */
			for (int i = 0; i < warningTitle.length; i++) {
				warningSheet.addCell(new Label(i, 0,warningTitle[i],wcf_center));
			}   
			warningSheet.setColumnView(0,12);//车牌号码
			warningSheet.setColumnView(1,20);//所属企业
			warningSheet.setColumnView(2,12);//联系人
			warningSheet.setColumnView(3,12);//联系电话
			warningSheet.setColumnView(4,20);//主管部门
			warningSheet.setColumnView(5,12);//预警类型
			warningSheet.setColumnView(6,20);//预警时间
			warningSheet.setColumnView(7,30);//预警信息
			warningSheet.setColumnView(8,12);//处置状态
			warningSheet.setColumnView(9,12);//抄报状态
			/** ***************正文数据********************* */
			if(warningList.size()>0){
				Field[] warningFields=null;
				int i=1;
				for(Object obj:warningList){
					warningFields=obj.getClass().getDeclaredFields();
					int j=0;
					for(Field v:warningFields){
						v.setAccessible(true);
						Object va=v.get(obj);
						if(va==null){
							va="";
						}
						warningSheet.addCell(new Label(j, i,va.toString(),wcf_left));
						j++;
					}
					i++;
				}
			}
			/**************************************************************预警工作表 end********************************************/
			
			/**************************************************************事故工作表 start********************************************/
			/** **********创建工作表************ */
			WritableSheet accidentSheet = workbook.createSheet("事故信息", 1);
			/** **********设置纵横打印（默认为纵打）、打印纸***************** */
			jxl.SheetSettings accidentSheetset = accidentSheet.getSettings();
			accidentSheetset.setProtected(false);

			/** ***************第一行列标题********************* */
			for (int a = 0; a < accidentTitle.length; a++) {
				accidentSheet.addCell(new Label(a, 0,accidentTitle[a],wcf_center));
			}   
			accidentSheet.setColumnView(0,12);//车牌号
			accidentSheet.setColumnView(1,20);//所属企业
			accidentSheet.setColumnView(2,12);//联系人
			accidentSheet.setColumnView(3,12);//联系电话
			accidentSheet.setColumnView(4,20);//主管部门
			accidentSheet.setColumnView(5,15);//事故类型
			accidentSheet.setColumnView(6,20);//事故发生时间
			accidentSheet.setColumnView(7,50);//事故发生地点
			accidentSheet.setColumnView(8,15);//事故责任
			/** ***************正文数据********************* */
			if(accidentList.size()>0){
				Field[] accidentFields=null;
				int at=1;
				for(Object obj:accidentList){
					accidentFields=obj.getClass().getDeclaredFields();
					int j=0;
					for(Field v:accidentFields){
						v.setAccessible(true);
						Object va=v.get(obj);
						if(va==null){
							va="";
						}
						accidentSheet.addCell(new Label(j, at,va.toString(),wcf_left));
						j++;
					}
					at++;
				}
			}
			/**************************************************************事故工作表 end********************************************/
			
			/**************************************************************违法工作表 start********************************************/
			/** **********创建工作表************ */
			WritableSheet illegalSheet = workbook.createSheet("违法信息", 2);
			/** **********设置纵横打印（默认为纵打）、打印纸***************** */
			jxl.SheetSettings illegalSheetset = illegalSheet.getSettings();
			illegalSheetset.setProtected(false);

			/** ***************第一行列标题********************* */
			for (int l = 0; l < illegalTitle.length; l++) {
				illegalSheet.addCell(new Label(l, 0,illegalTitle[l],wcf_center));
			}   
			illegalSheet.setColumnView(0,12);//车牌号码
			illegalSheet.setColumnView(1,20);//所属企业
			illegalSheet.setColumnView(2,12);//联系人
			illegalSheet.setColumnView(3,12);//联系电话
			illegalSheet.setColumnView(4,20);//主管部门
			illegalSheet.setColumnView(5,15);//违法类型
			illegalSheet.setColumnView(6,20);//违法时间
			illegalSheet.setColumnView(7,50);//违法地点
			illegalSheet.setColumnView(8,50);//违法行为
			/** ***************正文数据********************* */
			if(illegalList.size()>0){
				Field[] illegalFields=null;
				int il=1;
				for(Object obj:illegalList){
					illegalFields=obj.getClass().getDeclaredFields();
					int j=0;
					for(Field v:illegalFields){
						v.setAccessible(true);
						Object va=v.get(obj);
						if(va==null){
							va="";
						}
						illegalSheet.addCell(new Label(j, il,va.toString(),wcf_left));
						j++;
					}
					il++;
				}
			}
			/**************************************************************违法工作表 end********************************************/

			/** **********将以上缓存中的内容写到EXCEL文件中******** */
			workbook.write();
			/** *********关闭文件************* */
			workbook.close();   

		} catch (Exception e) {
			result="系统提示：Excel文件导出失败，原因："+ e.toString();
			System.out.println(result); 
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * @param fileName EXCEL文件名称
	 * @param listTitle EXCEL文件第一行列标题集合
	 * @param listContent EXCEL文件正文数据集合
	 * @return
	 */
	public  final static String exportExcelOld(HttpServletResponse response, String fileName,
			String BigTitle, String[] Title, List<Object> listContent,String[] Total,String exportType) {
		String result="系统提示：Excel文件导出成功！";  
		// 以下开始输出到EXCEL
		try {    
			//定义输出流，以便打开保存对话框______________________begin
			//OutputStream os = response.getOutputStream();// 取得输出流    
			ServletOutputStream os = response.getOutputStream();
			response.reset();// 清空输出流      
			response.setHeader("Content-disposition", "attachment; filename="+ new String(fileName.getBytes("GB2312"),"ISO8859-1"));
			// 设定输出文件头      
			response.setContentType("application/msexcel");// 定义输出类型    
			//定义输出流，以便打开保存对话框_______________________end

			/** **********创建工作簿************ */
			WritableWorkbook workbook = Workbook.createWorkbook(os);

			/** **********创建工作表************ */

			WritableSheet sheet = workbook.createSheet("Sheet1", 0);

			/** **********设置纵横打印（默认为纵打）、打印纸***************** */
			jxl.SheetSettings sheetset = sheet.getSettings();
			sheetset.setProtected(false);


			/** ************设置单元格字体************** */
			WritableFont NormalFont = new WritableFont(WritableFont.ARIAL, 10);
			WritableFont BoldFont = new WritableFont(WritableFont.ARIAL, 10,WritableFont.NO_BOLD);

			/** ************以下设置三种单元格样式，灵活备用************ */
			// 用于标题居中
			WritableCellFormat wcf_center = new WritableCellFormat(BoldFont);
			wcf_center.setBorder(Border.NONE, BorderLineStyle.THIN); // 线条
			wcf_center.setVerticalAlignment(VerticalAlignment.CENTRE); // 文字垂直对齐
			wcf_center.setAlignment(Alignment.LEFT); // 文字水平对齐
			wcf_center.setWrap(false); // 文字是否换行

			// 用于正文居左
			WritableCellFormat wcf_left = new WritableCellFormat(NormalFont);
			wcf_left.setBorder(Border.NONE, BorderLineStyle.THIN); // 线条
			wcf_left.setVerticalAlignment(VerticalAlignment.CENTRE); // 文字垂直对齐
			wcf_left.setAlignment(Alignment.LEFT); // 文字水平对齐
			wcf_left.setWrap(true); // 文字是否换行   

			// 用于表底合计
			WritableCellFormat wcf_bottom = new WritableCellFormat(NormalFont);
			wcf_bottom.setBorder(Border.NONE, BorderLineStyle.THIN); // 线条
			wcf_bottom.setVerticalAlignment(VerticalAlignment.CENTRE); // 文字垂直对齐
			wcf_bottom.setAlignment(Alignment.CENTRE); // 文字水平对齐
			wcf_bottom.setWrap(false); // 文字是否换行 

			/** ***************以下是EXCEL开头大标题，暂时省略********************* */
			//   sheet.mergeCells(0, 0, Title.length-1, 0);
			//   sheet.addCell(new Label(0, 0, BigTitle, wcf_center));

			/******************根据文字内容自动设置列宽，无效待优化*********************/
			//   CellView cellView = new CellView();  
			//   cellView.setAutosize(true); //设置自动大小  

			/** ***************以下是EXCEL第一行列标题********************* */
			for (int i = 0; i < Title.length; i++) {
				sheet.addCell(new Label(i, 0,Title[i],wcf_center));
				//sheet.setColumnView(i, cellView);
			}   
			if(exportType.equals("warning")){
				sheet.setColumnView(0,12);
				sheet.setColumnView(1,12);
				sheet.setColumnView(2,25);
				sheet.setColumnView(3,30);
				sheet.setColumnView(4,12);
				sheet.setColumnView(5,12);
			}else if(exportType.equals("accident")){
				sheet.setColumnView(0,12);
				sheet.setColumnView(1,15);
				sheet.setColumnView(2,25);
				sheet.setColumnView(3,30);
				sheet.setColumnView(4,15);
			}else if(exportType.equals("illegal")){
				sheet.setColumnView(0,12);
				sheet.setColumnView(1,15);
				sheet.setColumnView(2,25);
				sheet.setColumnView(3,30);
				sheet.setColumnView(4,30);
			}
			/** ***************以下是EXCEL正文数据********************* */
			Field[] fields=null;
			int i=1;
			for(Object obj:listContent){
				fields=obj.getClass().getDeclaredFields();
				int j=0;
				for(Field v:fields){
					v.setAccessible(true);
					Object va=v.get(obj);
					if(va==null){
						va="";
					}
					sheet.addCell(new Label(j, i,va.toString(),wcf_left));
					j++;
				}
				i++;
			}

			/** ***************以下是EXCEL表底合计数据********************* */
			for (int t = 0; t < Total.length; t++) {
				sheet.addCell(new Label(t, i+1,Total[t],wcf_bottom));
			}

			/** **********将以上缓存中的内容写到EXCEL文件中******** */
			workbook.write();
			/** *********关闭文件************* */
			workbook.close();   

		} catch (Exception e) {
			result="系统提示：Excel文件导出失败，原因："+ e.toString();
			System.out.println(result); 
			e.printStackTrace();
		}
		return result;
	}

	public  final static String exportExcel_list(HttpServletResponse response,String fileName,String[] Title, List<Object[]> listContent) {
		String result="系统提示：Excel文件导出成功！";  
		// 以下开始输出到EXCEL
		try {    
			//定义输出流，以便打开保存对话框______________________begin
			//	   HttpServletResponse response= ServletActionContext.getResponse();
			OutputStream os = response.getOutputStream();// 取得输出流      
			response.reset();// 清空输出流      
			response.setHeader("Content-disposition", "attachment; filename="+ new String(fileName.getBytes("GB2312"),"ISO8859-1"));
			// 设定输出文件头      
			response.setContentType("application/msexcel");// 定义输出类型    
			//定义输出流，以便打开保存对话框_______________________end

			/** **********创建工作簿************ */
			WritableWorkbook workbook = Workbook.createWorkbook(os);

			/** **********创建工作表************ */

			WritableSheet sheet = workbook.createSheet("Sheet1", 0);

			/** **********设置纵横打印（默认为纵打）、打印纸***************** */
			jxl.SheetSettings sheetset = sheet.getSettings();
			sheetset.setProtected(false);


			/** ************设置单元格字体************** */
			WritableFont NormalFont = new WritableFont(WritableFont.ARIAL, 10);
			WritableFont BoldFont = new WritableFont(WritableFont.ARIAL, 10,WritableFont.BOLD);

			/** ************以下设置三种单元格样式，灵活备用************ */
			// 用于标题居中
			WritableCellFormat wcf_center = new WritableCellFormat(BoldFont);
			wcf_center.setBorder(Border.ALL, BorderLineStyle.THIN); // 线条
			wcf_center.setVerticalAlignment(VerticalAlignment.CENTRE); // 文字垂直对齐
			wcf_center.setAlignment(Alignment.CENTRE); // 文字水平对齐
			wcf_center.setWrap(false); // 文字是否换行

			// 用于正文居左
			WritableCellFormat wcf_left = new WritableCellFormat(NormalFont);
			wcf_left.setBorder(Border.NONE, BorderLineStyle.THIN); // 线条
			wcf_left.setVerticalAlignment(VerticalAlignment.CENTRE); // 文字垂直对齐
			wcf_left.setAlignment(Alignment.LEFT); // 文字水平对齐
			wcf_left.setWrap(false); // 文字是否换行   


			/** ***************以下是EXCEL开头大标题，暂时省略********************* */
			//sheet.mergeCells(0, 0, colWidth, 0);
			//sheet.addCell(new Label(0, 0, "XX报表", wcf_center));
			/** ***************以下是EXCEL第一行列标题********************* */
			for (int i = 0; i < Title.length; i++) {
				sheet.addCell(new Label(i, 0,Title[i],wcf_center));
			}   
			/** ***************以下是EXCEL正文数据********************* */
			Field[] fields=null;
			int i=1;
			for(Object[] obj:listContent){
				for(int j=0;j<=obj.length-2;j++){
					sheet.addCell(new Label(j, i,obj[j]+"",wcf_left));
				}
				i++;
			}
			/** **********将以上缓存中的内容写到EXCEL文件中******** */
			workbook.write();
			/** *********关闭文件************* */
			workbook.close();   

		} catch (Exception e) {
			result="系统提示：Excel文件导出失败，原因："+ e.toString();
			System.out.println(result); 
			e.printStackTrace();
		}
		return result;
	}

	/***************************************************************************
	 * @param fileName EXCEL文件名称
	 * @param listTitle EXCEL文件第一行列标题集合
	 * @param listContent EXCEL文件正文数据集合
	 * @return
	 */
	public   final static String exportExcel_map(HttpServletResponse response, String fileName,String[] Title, List<HashMap<String, Integer>> listContent) {
		String result="系统提示：Excel文件导出成功！";  
		// 以下开始输出到EXCEL
		try {    
			//定义输出流，以便打开保存对话框______________________begin
			//   HttpServletResponse response=ServletActionContext.getResponse();
			//	   HttpServletResponse response=SysContent.getResponse();
			OutputStream os = response.getOutputStream();// 取得输出流      
			response.reset();// 清空输出流      
			response.setHeader("Content-disposition", "attachment; filename="+ new String(fileName.getBytes("GB2312"),"ISO8859-1"));
			// 设定输出文件头      
			response.setContentType("application/msexcel");// 定义输出类型    
			//定义输出流，以便打开保存对话框_______________________end

			/** **********创建工作簿************ */
			WritableWorkbook workbook = Workbook.createWorkbook(os);

			/** **********创建工作表************ */

			WritableSheet sheet = workbook.createSheet("Sheet1", 0);

			/** **********设置纵横打印（默认为纵打）、打印纸***************** */
			jxl.SheetSettings sheetset = sheet.getSettings();
			sheetset.setProtected(false);


			/** ************设置单元格字体************** */
			WritableFont NormalFont = new WritableFont(WritableFont.ARIAL, 10);
			WritableFont BoldFont = new WritableFont(WritableFont.ARIAL, 10,WritableFont.BOLD);

			/** ************以下设置三种单元格样式，灵活备用************ */
			// 用于标题居中
			WritableCellFormat wcf_center = new WritableCellFormat(BoldFont);
			wcf_center.setBorder(Border.ALL, BorderLineStyle.THIN); // 线条
			wcf_center.setVerticalAlignment(VerticalAlignment.CENTRE); // 文字垂直对齐
			wcf_center.setAlignment(Alignment.CENTRE); // 文字水平对齐
			wcf_center.setWrap(false); // 文字是否换行

			// 用于正文居左
			WritableCellFormat wcf_left = new WritableCellFormat(NormalFont);
			wcf_left.setBorder(Border.NONE, BorderLineStyle.THIN); // 线条
			wcf_left.setVerticalAlignment(VerticalAlignment.CENTRE); // 文字垂直对齐
			wcf_left.setAlignment(Alignment.LEFT); // 文字水平对齐
			wcf_left.setWrap(false); // 文字是否换行   


			/** ***************以下是EXCEL开头大标题，暂时省略********************* */
			//sheet.mergeCells(0, 0, colWidth, 0);
			//sheet.addCell(new Label(0, 0, "XX报表", wcf_center));
			/** ***************以下是EXCEL第一行列标题********************* */
			for (int i = 0; i < Title.length; i++) {
				sheet.addCell(new Label(i, 0,Title[i],wcf_center));
			}   
			/** ***************以下是EXCEL正文数据********************* */
			Field[] fields=null;
			int i=1;
			for(Map<String, Integer> map:listContent){
				int j=0;
				for(Object key:map.keySet()){	
					sheet.addCell(new Label(j, i,map.get(key)+"",wcf_left));
					j++;
				}
				i++;
			}
			/** **********将以上缓存中的内容写到EXCEL文件中******** */
			workbook.write();
			/** *********关闭文件************* */
			workbook.close();   

		} catch (Exception e) {
			result="系统提示：Excel文件导出失败，原因："+ e.toString();
			System.out.println(result); 
			e.printStackTrace();
		}
		return result;
	}

}
