package top.fanxfan.jpa.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import top.fanxfan.jpa.repository.QuestionCatalogRepository;
import top.fanxfan.jpa.service.QuestionCatalogService;

/**
 * 试题分类Service实现
 *
 * @author fanxfan
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class QuestionCatalogServiceImpl implements QuestionCatalogService {

    private final QuestionCatalogRepository questionCatalogRepository;
}
