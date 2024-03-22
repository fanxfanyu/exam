package top.fanxfan.exam.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.fanxfan.exam.service.QuestionOptionService;

/**
 * 试题选项Controller
 *
 * @author fanxfan
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/question/option")
@Slf4j
public class QuestionOptionController {

    private final QuestionOptionService questionOptionService;
}
