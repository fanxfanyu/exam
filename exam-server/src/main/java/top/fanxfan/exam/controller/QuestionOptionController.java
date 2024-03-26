package top.fanxfan.exam.controller;

import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.dev33.satoken.annotation.SaCheckPermission;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
@SaCheckLogin
@Slf4j
public class QuestionOptionController {

    private final QuestionOptionService questionOptionService;

    /**
     * 删除选项
     *
     * @param id 选项ID
     * @return 结果
     */
    @DeleteMapping("/{id}")
    @SaCheckPermission(value = "question:option:delete", orRole = "super")
    public ResponseEntity<Boolean> delete(@PathVariable Long id) {
        return ResponseEntity.ok(questionOptionService.remove(id));
    }
}
