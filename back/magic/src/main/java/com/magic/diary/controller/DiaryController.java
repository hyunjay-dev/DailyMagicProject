package com.magic.diary.controller;

import java.io.File;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.swing.filechooser.FileSystemView;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;


import com.magic.diary.domain.Diary;
import com.magic.diary.service.DiaryService;

@RestController
@RequestMapping("magic")
@CrossOrigin(origins = {"*"}, maxAge = 6000)
public class DiaryController {

   @Autowired
   private DiaryService service;

   
   @PostMapping("addDiary")
   public ResponseEntity addDiary(@RequestBody Diary diary) throws Exception{
//      diary.setDiaryContents(diary.getDiaryContents().replaceAll("(\r\n|\r|\n|\n\r)", "<br>"));
      service.addDiary(diary);
      return new ResponseEntity(HttpStatus.OK);
   }
   
   @PutMapping("updateDiary")
   public ResponseEntity updateDiary(@RequestBody Diary diary) throws Exception{
      diary.setDiaryContents(diary.getDiaryContents().replaceAll("(\r\n|\r|\n|\n\r)", "<br>"));
      boolean result = service.updateDiary(diary);
      if(!result) return new ResponseEntity(HttpStatus.NO_CONTENT);
      return new ResponseEntity(HttpStatus.OK);
   }
   
   @DeleteMapping("deleteDiary/{diaryId}")
   public ResponseEntity deleteDiary(@PathVariable int diaryId) throws Exception{
      boolean result = service.deleteDiary(diaryId);
      if(!result) return new ResponseEntity(HttpStatus.NO_CONTENT);
      return new ResponseEntity(HttpStatus.OK);
   }
   
   @GetMapping("getDiary/{diaryId}")
   public ResponseEntity getDiary(@PathVariable int diaryId) throws Exception{
      System.out.println("aaa");
      Diary diary = service.getDiary(diaryId);
      diary.setDiaryContents(diary.getDiaryContents().replaceAll("<br>", "\n"));
      if(diary == null) {
         System.out.println("aaaaaaa");
         return new ResponseEntity(HttpStatus.NO_CONTENT);
      }
      System.out.println("vvvvvv");
      return new ResponseEntity(diary, HttpStatus.OK);
   }
   
   @GetMapping("getAllDiary/{memId}") // page?!
   public ResponseEntity getAllDiary(@PathVariable String memId) throws Exception{
      List<Diary> diarys = service.getAllDiary(memId);
      if(diarys.isEmpty()) return new ResponseEntity(HttpStatus.NO_CONTENT);
      return new ResponseEntity(diarys, HttpStatus.OK);
   }
   
   @GetMapping("countDiary/{memId}")
   public ResponseEntity countDiary(@PathVariable String memId) throws Exception{
      return new ResponseEntity(service.countDiary(memId) ,HttpStatus.OK);
   }
   
   @GetMapping("countLabels/{memId}")
   public ResponseEntity countLabels(@PathVariable String memId) throws Exception{
      return new ResponseEntity(service.countLabels(memId), HttpStatus.OK);
   }
   
   // mapper 참고해서 작성
   
   @PostMapping("imageUpload")
   public ResponseEntity uploadSingle(@RequestParam("image") MultipartFile file, @RequestParam("name") String fileName, HttpServletRequest request) throws Exception {
//       String rootPath = FileSystemView.getFileSystemView().getHomeDirectory().toString();
//       String basePath = rootPath + "\\" + "single";
//       String filePath = basePath + "\\" + file.getOriginalFilename();
//       File dest = new File(filePath);
       
      String path = "/Users/HyeonJae/nhj/final_project/final_web/Web/EmotionalClassificationWeb/back/magic/src/main/resources/static/image/"+fileName;
//      String root = request.getSession().getServletContext().getRealPath("/");
//      String path = root+"resources\\upload\\"+ fileName;
      
//      String path = "classpath:/static/upload"+fileName;
//      System.out.println(path);
       
      File copyFile = new File(path);
       file.transferTo(copyFile); // 파일 업로드 작업 수행 // 정해놓은 폴더에 업로
       return new ResponseEntity(path, HttpStatus.OK); // 여기서 패스를 리턴해서 플라스크가 여기로 접근해서 모델에 넣어서 분석시킬 수 있음
   }
   
   @PostMapping("fileDown.do")
   public ModelAndView filedown(String filename, HttpServletRequest request) throws Exception{
      System.out.println("filename::" + filename);
      String root = request.getSession().getServletContext().getRealPath("/");
      String path = root+"\\upload\\";
      
      HashMap map = new HashMap();
      map.put("path", path); //업로드된 파일이 현재 path에 저장되어 있다. 여기에 있는 특정한 파일을 다운로드 한다.
      
      //실제 출력결과 페이지 없는 빈 처리는 BeanNameViewResolver
      
      return new ModelAndView("downloadView", map);
   }
}