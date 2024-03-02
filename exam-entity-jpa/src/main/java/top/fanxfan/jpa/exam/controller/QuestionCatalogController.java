package top.fanxfan.jpa.exam.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.fanxfan.jpa.exam.service.QuestionCatalogService;

/**
 * 试题分类Controller
 *
 * @author fanxfan
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/question/catalog")
@Slf4j
public class QuestionCatalogController {

    private final QuestionCatalogService questionCatalogService;
}
