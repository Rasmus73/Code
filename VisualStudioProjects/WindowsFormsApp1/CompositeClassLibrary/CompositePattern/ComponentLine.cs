﻿//using System;
//using System.Collections.Generic;
//using System.Drawing;
//using System.Linq;
//using System.Text;
//using System.Threading.Tasks;

//namespace CompositeClassLibrary.CompositePattern
//{
//    internal class ComponentLine : IComponent
//    {
//        public string Name { get; set; }

//        public ComponentLine(string name)
//        {
//            Name = name;
//        }

//        public void Insert(IComponent component, int index)
//        {
//            throw new NotImplementedException();
//        }

//        public void Remove(IComponent component)
//        {
//            throw new NotImplementedException();
//        }

//        public IComponent GetChild(int index)
//        {
//            throw new NotImplementedException();
//        }

//        public void Draw(Graphics graphics)
//        {
//            System.Drawing.Font font = new Font("TimesNewRoman", 8);
//            graphics.DrawString(Name, font, new SolidBrush(Color.Black), 1, 20);
//        }
//    }
//}