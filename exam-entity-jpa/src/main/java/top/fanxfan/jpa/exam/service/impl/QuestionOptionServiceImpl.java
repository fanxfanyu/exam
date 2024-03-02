package top.fanxfan.jpa.exam.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import top.fanxfan.jpa.exam.repository.QuestionOptionRepository;
import top.fanxfan.jpa.exam.service.QuestionOptionService;

/**
 * 试题选项Service实现
 *
 * @author fanxfan
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class QuestionOptionServiceImpl implements QuestionOptionService {

    private final QuestionOptionRepository questionOptionRepository;
}
