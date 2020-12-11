package com.increpas.cls.service;

import java.io.File;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.*;
import org.springframework.web.multipart.*;
import org.springframework.web.servlet.ModelAndView;

import com.increpas.cls.controller.Board;
import com.increpas.cls.dao.*;
import com.increpas.cls.util.*;
import java.util.*;
import com.increpas.cls.vo.*;

public class BoardService {
	@Autowired
	BoardDao bDao;
	@Autowired
	FileUtil fUtil;
	private static final Logger logger = LoggerFactory.getLogger(BoardService.class);
	// 게시글 등록 처리함수
	public void addBoard(ModelAndView mv, BoardVO bVO, PageUtil page) {
		// 먼저 파일 정보들을 완성하고(파일 원이름, 저장이름, 길이, ...)
		String dir = "/img/upload/";
		ArrayList<FileVO> list = fUtil.saveProc(bVO.getFile(), dir);
		// BoardVO 의 list에 채워주고
		bVO.setList(list);
		
		try {			
			bDao.addAll(bVO);
			
			// 데이터베이스 작업이 완료되면 게시글 리스트 첫 페이지로 보낸다.
			page.setNowPage(1);
			mv.addObject("VIEW", "/cls/board/boardList.cls");
		} catch(Exception e) {
			e.printStackTrace();
			mv.addObject("VIEW", "/cls/board/boardWrite.cls");
			System.out.println("### 글 등록 실패 ###");
		}
		mv.addObject("PAGE", page);
		mv.setViewName("board/boardRedirect");
		return;
	}
	
	// 게시글 수정 처리함수
	public void editBoard(ModelAndView mv, BoardVO bVO, PageUtil page) {
		// 1. 추가된 파일을 찾아낸다.
		int fileCnt = addEditFile(bVO);
		// 2. 추가된 파일 갯수가 1개 이상이면 파일을 추가해준다.
		if(fileCnt > 0) {
			ArrayList<FileVO> list = fUtil.saveProc(bVO.getFile(), "/img/upload");
			for(FileVO fVO : list) {
				fVO.setBno(bVO.getBno());
				bDao.addFile(fVO);
			}
		}
		// 3. 삭제할 파일을 찾아낸다.
		int delFileCnt = delEditFile(bVO);
		// 4. 삭제할 파일 갯수가 1개 이상이면 파일을 삭제한다.
		if(delFileCnt > 0) {
			bDao.delFile(bVO.getDelfile());
		}
		
		// 3. 게시글을 수정한다.
		int cnt = 0;
		cnt = bDao.boardEdit(bVO);
		if(cnt == 0) {
			logger.info("***** " + bVO.getBno() + " 번째 게시글 수정에 실패 *****");
			mv.addObject("VIEW", "/cls/board/boardEdit.cls");
		} else {
			logger.info("***** " + bVO.getBno() + " 번째 게시글 수정에 성공( 추가된 파일 갯수 : " + fileCnt + ", 삭제된 파일 갯수 : " + delFileCnt + " )");
			mv.addObject("VIEW", "/cls/board/boardList.cls");
		}
		mv.addObject("PAGE", page);
		mv.setViewName("board/boardRedirect");
	}
	
	// 게시글 수정 시 추가된 파일 처리함수
	public int addEditFile(BoardVO bVO) {
		// 추가된 파일 갯수를 알아낸다.
		int fileCnt = 0;
		
		try {
			MultipartFile[] files = bVO.getFile();
			fileCnt = files.length;
//			System.out.println("추가된 파일 갯수 : " + fileCnt);
		} catch(NullPointerException e) {
			// 추가된 파일 갯수가 없으면 그냥 0을 보낸다.
			return fileCnt;
		}
		
		return fileCnt;
	}
	
	// 게시글 수정 시 삭제된 파일 처리함수
	public int delEditFile(BoardVO bVO) {
		int fileCnt = 0;
		
		try {
			int[] delfile = bVO.getDelfile();
			fileCnt = delfile.length;
		} catch(NullPointerException e) {
			return fileCnt;
		}
		
		return fileCnt;
	}
}
