using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

using Xamarin.Forms;
using Xamarin.Forms.Xaml;

namespace yx_marketplace
{
    [XamlCompilation(XamlCompilationOptions.Compile)]
    public partial class CategoryPage : ContentPage
    {
        public CategoryPage()
        {
            InitializeComponent();
            var assembly = typeof(CategoryPage);
            gameImage.Source = ImageSource.FromResource("yx_marketplace.Assets.Images.GameTab.png",assembly);
            EducationImage.Source = ImageSource.FromResource("yx_marketplace.Assets.Images.EducationTab.png", assembly);
            SocialMediaImage.Source = ImageSource.FromResource("yx_marketplace.Assets.Images.SocialMedia.jpg", assembly);
        }
        private void game_click(object sender, EventArgs e)
        {
            Navigation.PushAsync(new AppList());
        }
    }
}