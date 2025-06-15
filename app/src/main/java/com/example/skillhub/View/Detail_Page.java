package com.example.skillhub.View;

import android.os.Bundle;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.bumptech.glide.Glide;
import com.example.skillhub.Model.models.Courses;
import com.example.skillhub.R;
import com.example.skillhub.Utils.pictureFormat;
import com.example.skillhub.ViewModel.CourseViewModel;
import com.example.skillhub.databinding.ActivityDetailPageBinding;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.NodeTraversor;
import org.jsoup.select.NodeVisitor;

public class Detail_Page extends AppCompatActivity
{
    CourseViewModel viewmodels;
ActivityDetailPageBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                        | View.SYSTEM_UI_FLAG_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
        );
        super.onCreate(savedInstanceState);
        binding=ActivityDetailPageBinding.inflate(getLayoutInflater());

        EdgeToEdge.enable(this);
        setContentView(binding.getRoot());
        viewmodels = new ViewModelProvider(this).get(CourseViewModel.class);
        viewmodels.fetchSingleCourse(getIntent().getIntExtra("id",0));
        viewmodels.getSingleCourse().observe(this, new Observer<Courses>() {
            @Override
            public void onChanged(Courses course) {
                if (course != null) {
//                    Toast.makeText(Detail_Page.this, "" + course.detail, Toast.LENGTH_SHORT).show();
                    String path = pictureFormat.getCorrect_path(course.thumbnail);
                    Glide.with(Detail_Page.this).load(path).into(binding.imageView5);
                    binding.singleTitle.setText(course.title);
                    binding.description.setText(course.description);
                    binding.detail.setText(Html.fromHtml(course.detail, Html.FROM_HTML_MODE_LEGACY));
                    binding.detail.setMovementMethod(LinkMovementMethod.getInstance());

                } else {
                    Toast.makeText(Detail_Page.this, "Course data is null", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }

    private String translateHtml(String html) {
        Document document = Jsoup.parse(html);

        // Traverse all text nodes and "translate" (replace with mock translation)
        NodeTraversor.traverse(new NodeVisitor() {
            @Override
            public void head(org.jsoup.nodes.Node node, int depth) {
                if (node instanceof org.jsoup.nodes.TextNode) {
                    org.jsoup.nodes.TextNode textNode = (org.jsoup.nodes.TextNode) node;
                    String originalText = textNode.text().trim();
                    if (!originalText.isEmpty()) {
                        String translatedText = mockTranslate(originalText);
                        textNode.text(translatedText);
                    }
                }
            }

            @Override
            public void tail(org.jsoup.nodes.Node node, int depth) {
                // No-op
            }
        }, document.body());

        // Return translated HTML
        return document.body().html();
    }

    // Mock translation function
    private String mockTranslate(String input) {
        // Replace with real translation API call if needed
        return "[Translated] " + input;
    }

}