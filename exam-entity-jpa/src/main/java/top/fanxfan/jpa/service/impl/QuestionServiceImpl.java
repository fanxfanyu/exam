package top.fanxfan.jpa.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import top.fanxfan.jpa.repository.QuestionRepository;
import top.fanxfan.jpa.service.QuestionService;

/**
 * 试题Service实现
 *
 * @author fanxfan
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class QuestionServiceImpl implements QuestionService {

    private final QuestionRepository questionRepository;
}
