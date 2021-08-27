package com.mindaces.mindaces.controller;

import com.mindaces.mindaces.dto.BoardDto;
import com.mindaces.mindaces.service.BoardService;
import com.mindaces.mindaces.service.GalleryService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

//보여주거나 갤러리에 관련된것만 (board 수정하는건 BoardController)
@Controller
@RequestMapping(value = "/gallery")
public class GalleryController
{
    BoardService boardService;
    GalleryService galleryService;

    String errorURL = "redirect:/error/galleryMiss";

    public GalleryController(BoardService boardService, GalleryService galleryService)
    {
        this.boardService = boardService;
        this.galleryService = galleryService;
    }

    @GetMapping(value = "/galleryList" )
    public String galleryList(
            Model model)
    {

        model.addAttribute("galleryList",galleryService.getGalleryList());
        return "gallery/galleryList";
    }

    //갤러리 목록 or 갤러리 메인 페이지
    @GetMapping(value = "/{galleryName}" )
    public String galleryContentList(
            Model model,
            @RequestParam(required = false,defaultValue = "1") Integer page,
            @PathVariable(name = "galleryName") String galleryName)
    {
        Boolean isGallery = galleryService.isGallery(galleryName);
        if(!isGallery)
        {
            return errorURL;
        }
        List<BoardDto> boardDtoList = boardService.getGalleryPost(galleryName,page);
        Integer[] pageList = boardService.getPageList(galleryName,page);


        model.addAttribute("pageList",pageList);
        model.addAttribute("postList",boardDtoList);
        model.addAttribute("galleryName",galleryName);
        return "gallery/galleryContentList";
    }

    //글 내용 보여주기
    @GetMapping(value = "/{galleryName}/{index}")
    public String postContent(
            Model model,
            @PathVariable(name="galleryName") String galleryName,
            @PathVariable(name="index") Long contentIdx
    )
    {
        Boolean isGallery = galleryService.isGallery(galleryName);
        if(!isGallery)
        {
            return errorURL;
        }

        BoardDto boardDto = boardService.getBoardByIdx(galleryName,contentIdx);
        model.addAttribute("board",boardDto);
        return "gallery/postContent";
    }



}
