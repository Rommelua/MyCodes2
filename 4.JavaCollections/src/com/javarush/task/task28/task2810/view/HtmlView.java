package com.javarush.task.task28.task2810.view;

import com.javarush.task.task28.task2810.Controller;
import com.javarush.task.task28.task2810.vo.Vacancy;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class HtmlView implements View {
    private Controller controller;
    private final String filePath = "./4.JavaCollections/src/"
            + this.getClass().getPackage().getName().replace('.', '/') + "/vacancies.html";

    @Override
    public void update(List<Vacancy> vacancies) {
        String s = getUpdatedFileContent(vacancies);
        updateFile(s);
    }

    @Override
    public void setController(Controller controller) {
        this.controller = controller;
    }

    public void userCitySelectEmulationMethod() {
        controller.onCitySelect("Odessa");
    }

    private String getUpdatedFileContent(List<Vacancy> vacancies) {
        Document doc;
        try {
            doc = getDocument();
        } catch (IOException e) {
            e.printStackTrace();
            return "Some exception occurred";
        }
        Element e = doc.getElementsByClass("template").first();
        Element copy = e.clone();
        copy.removeAttr("style");
        copy.removeClass("template");
        for (Element element : doc.getElementsByTag("tr")) {
            if (element.hasClass("vacancy") && !element.hasClass("template")) {
                element.remove();
            }
        }
        for (Vacancy vacancy : vacancies) {
            Element clone = copy.clone();
            clone.getElementsByClass("city").first().appendText(vacancy.getCity());
            clone.getElementsByClass("companyName").first().appendText(vacancy.getCompanyName());
            clone.getElementsByClass("salary").first().appendText(vacancy.getSalary());
            Element title = clone.getElementsByClass("title").first()
                    .getElementsByTag("a").first();
            title.appendText(vacancy.getTitle());
            title.attr("href", vacancy.getUrl());
            e.before(clone.outerHtml());
        }
        return doc.toString();
    }

    protected Document getDocument() throws IOException {
        return Jsoup.parse(new File(filePath), "UTF-8");
    }

    private void updateFile(String s) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write(s);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
