using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;
using ClassLibrary;

namespace WindowsFormsApplication1
{
    public partial class Form1 : Form
    {
        public Form1()
        {
            InitializeComponent();
        }

        private MyPage myPage;

        private DateTime dt1_start = DateTime.Now.AddDays(-100);
        private DateTime dt1_slut = DateTime.Now.AddDays(100);

        private DateTime dt2_start = DateTime.Now.AddDays(200);
        private DateTime dt2_slut = DateTime.Now.AddDays(310);

        private DateTime dt3_start = DateTime.Now.AddDays(200);
        private DateTime dt3_slut = DateTime.Now.AddDays(500);

        private DateTime dt4_start = DateTime.Now.AddDays(100);
        private DateTime dt4_slut = DateTime.Now.AddDays(700);


        private void Form1_Load(object sender, EventArgs e)
        {            
            myPage = new MyPage(Width);

            //myPage.AddInterval(dt1_start, dt1_slut, "DT1");
            //myPage.AddInterval(dt2_start, dt2_slut, "DT2");
            //myPage.AddInterval(dt3_start, dt3_slut, "DT3");
        }

        private void button1_Click(object sender, EventArgs e)
        {
            myPage = new MyPage(Width);

            myPage.AddInterval(dt1_start, dt1_slut, "DT1");
            myPage.AddInterval(dt2_start, dt2_slut, "DT2");
            myPage.AddInterval(dt3_start, dt3_slut, "DT3");
            myPage.AddInterval(dt4_start, dt4_slut, "DT4");

            System.Drawing.Pen myPen;
            myPen = new System.Drawing.Pen(System.Drawing.Color.Black);
            System.Drawing.Graphics formGraphics = this.CreateGraphics();

            formGraphics.Clear(Color.AliceBlue);

            //var ll = myPage.GetIntervals();

            //int y = 100;
            //foreach (var l in ll)
            //{
            //    formGraphics.DrawLine(myPen, l.StartX, y, l.EndX, y);
            //    formGraphics.DrawString(l.Comment, DefaultFont, new SolidBrush(Color.Black), l.StartX, y);
            //    y += 100;
            //}

            myPen.Dispose();
            formGraphics.Dispose();
        }
    }
}